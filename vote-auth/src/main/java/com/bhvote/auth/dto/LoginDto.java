package com.bhvote.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    //用户ID(微信编号)
    @NotNull(message = "账号不能为空")
    private Long userId;
    //密码
    @NotBlank(message = "密码不能为空")
    private String userPassword;
}
