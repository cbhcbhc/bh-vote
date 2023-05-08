package com.bhvote.auth.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2023-05-08 15:50:50
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("User")
public class User  {
    //用户ID@TableId
    private Long userId;

    //用户名
    private String userName;
    //邮箱
    private String userEmail;
    //密码
    private String userPassword;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;



}

