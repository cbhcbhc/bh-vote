package com.bhvote.vote.feign.feignvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //用户ID
    private Long id;

    //用户账号(微信小程序标识)
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
