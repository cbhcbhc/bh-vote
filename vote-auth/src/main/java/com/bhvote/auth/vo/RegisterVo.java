package com.bhvote.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVo {
    //用户账号(微信小程序标识)
    private Long userId;
    //用户名
    private String userName;
    //邮箱
    private String userEmail;
    //token
    private String token;

}
