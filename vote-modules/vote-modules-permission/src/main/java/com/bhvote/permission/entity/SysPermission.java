package com.bhvote.permission.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 权限表(SysPermission)表实体类
 *
 * @author makejava
 * @since 2023-05-09 09:10:37
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_permission")
public class SysPermission  {
    //权限ID
    @TableId
    private Long permissionId;

    //权限名称
    private String permissionName;
    //权限描述
    private String permissionDescription;
    //权限对应的资源URL
    private String url;
    //删除标志（0代表存在 1代表删除）
    private String delFlag;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;



}

