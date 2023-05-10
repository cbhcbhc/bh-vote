package com.bhvote.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bhvote.auth.dto.LoginDto;
import com.bhvote.auth.dto.RegisterDto;
import com.bhvote.auth.entity.User;
import com.bhvote.auth.service.UserService;
import com.bhvote.auth.vo.LoginVo;
import domain.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 登录认证模块
 */
@RestController
@RequestMapping("/auth")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 1. 账号注册
     * url: /auth/auth/register
     */
    @PostMapping("/register")
    public R register(@Valid @RequestBody RegisterDto registerDto){
        userService.register(registerDto);
        return R.ok().put("msg","账号注册成功！");
    }

    /**
     * 2.登录
     * url: /auth/auth/login
     */
    @PostMapping("/login")
    public R login(@Valid @RequestBody LoginDto loginDto){
        LoginVo loginVo = userService.login(loginDto);
        return R.ok().put("data",loginVo).put("msg","用户登录成功");
    }


    /**
     * 3. 后台注销
     * url: /auth/auth/logout
     */
    @PostMapping("/logout")
    public R logout() throws Exception {
        userService.logout();
        return R.ok().put("msg","账号注销成功");
    }

    /**
     * 4. 根据用户id查询用户信息
     * url: /auth/auth/getUserInfo/{userId}
     */
    @GetMapping("/getUserInfo/{userId}")
    public User getUserInfo(@PathVariable("userId") Long userId){
        LambdaQueryWrapper<User> w = new LambdaQueryWrapper<>();
        w.eq(User::getUserId,userId);
        User user = userService.getBaseMapper().selectOne(w);
        return user;
    }
}
