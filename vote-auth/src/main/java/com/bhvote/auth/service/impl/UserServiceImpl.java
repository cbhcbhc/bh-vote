package com.bhvote.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.auth.dto.LoginDto;
import com.bhvote.auth.dto.RegisterDto;
import com.bhvote.auth.entity.User;
import com.bhvote.auth.mapper.UserMapper;
import com.bhvote.auth.service.UserService;
import com.bhvote.redis.service.RedisService;
import enums.AppHttpCodeEnum;
import exception.SystemException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import utils.JwtUtil;
import utils.PasswordEncoderUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 15:43:25
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private RedisService redisService;
    @Override
    public void register(RegisterDto registerDto) {
        //1.判断账号是否已经注册
        Long userId = registerDto.getUserId();
        LambdaQueryWrapper<User> w = new LambdaQueryWrapper<>();
        w.eq(User::getUserId,registerDto.getUserId());
        User u = baseMapper.selectOne(w);
        if (u != null){
            throw new SystemException(AppHttpCodeEnum.USER_EXIST);
        }

        //2.注册
        User user = new User();
        user.setUserId(registerDto.getUserId());
        user.setUserName(registerDto.getUserName());
        user.setUserEmail(registerDto.getUserEmail());
        //密码加密存储
        user.setUserPassword(PasswordEncoderUtil.encode(registerDto.getUserPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        //保存
        save(user);

    }

    @Override
    public String login(LoginDto loginDto) {
        //1.判断账号是否存在
        LambdaQueryWrapper<User> w = new LambdaQueryWrapper<>();
        w.eq(User::getUserId,loginDto.getUserId());
        User user = baseMapper.selectOne(w);
        if (null == user){
            //账号未注册
            throw new SystemException(AppHttpCodeEnum.USER_NOT_EXIST);
        }

        //2. 校验密码是否正确,matches为true则代表校验成功
        boolean matches = PasswordEncoderUtil.matches(loginDto.getUserPassword(),user.getUserPassword());
        if (!matches){
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }


        /**
         * 3.1 根据userId生成token
         * 3.2 把token缓存到redis中
         */

        //3.1 根据userId生成token
        String token = JwtUtil.createJWT(String.valueOf(loginDto.getUserId()));

        //3.2 把token缓存到redis中  key为："login-"+"手机号"
        redisService.setCacheObject("login-"+loginDto.getUserId(),token);

        //4.把token返回给前端
        return token;
    }

    @Override
    public void logout() {
        //1. 从请求头中获取token
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 假设JWT放在请求头的Authorization字段中，且以"Bearer "开头
        //请求头： Authorization = Bearer +{token}
        String token = request.getHeader("Authorization").substring(7);

        if (token == null){
            throw new SystemException(AppHttpCodeEnum.TOKEN_EMPTY);
        }

        //2. 解析token
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            throw new SystemException(AppHttpCodeEnum.TOKEN_ERROR);
        }
        String userId = claims.getSubject();

        //3. 删除该用户在redis中的token缓存
        String tokenKey = "login-" + userId;
        redisService.deleteObject(tokenKey);
    }


}

