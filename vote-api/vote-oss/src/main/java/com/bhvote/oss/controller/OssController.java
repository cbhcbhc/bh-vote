package com.bhvote.oss.controller;

import com.bhvote.oss.config.OssConfig;
import com.google.gson.Gson;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import domain.R;
import enums.AppHttpCodeEnum;
import exception.SystemException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import utils.PathUtils;


import javax.annotation.Resource;
import java.io.InputStream;

/**
 * oss文件上传
 */
@RestController
@RequestMapping("/oss")
//该模块下的所有url访问时都要带上oss,详细可以看gateway中的配置
public class OssController {
    @Resource
    private OssConfig ossConfig;

    /**
     * 1.文件上传并返回外链
     * url: /oss/oss/upload
     * @param img
     * @return
     */
    @PostMapping("/upload")
    public R uploadImg(@RequestParam("img") MultipartFile img) {
        //判断文件类型

        //获取原始文件名
        String originalFilename = img.getOriginalFilename();


        //对原始文件进行判断
        if (!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg") ) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

        //判断文件大小，限制为5MB
        if (img.getSize() > 5 * 1024 * 1024) {
            throw new SystemException(AppHttpCodeEnum.FILE_SIZE_ERROR);
        }

        //如果判断通过上传文件到OSS
        String filePath = PathUtils.generateFilePath(originalFilename);
        String url = uploadOss(img, filePath);
        return R.ok().put("data", url).put("msg", "上传成功");
    }

    private String uploadOss(MultipartFile imgFile, String filePath) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;


        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(ossConfig.getAccessKey(), ossConfig.getSecretKey());
            String upToken = auth.uploadToken(ossConfig.getBucket());

            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                // 设置下载凭证有效期（秒）
                long expireInSeconds = 3600; // 设置为1小时，你可以根据需求进行调整
                // 生成下载凭证
                String privateUrl = auth.privateDownloadUrl(ossConfig.getDomainname() + key, expireInSeconds);
                //返回域名 + key + tokem
                return privateUrl;


            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        //出现异常则返回原始链接
        return ossConfig.getDomainname() + key;

    }

}

