package com.bhvote.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bhvote.auth.dto.LoginDto;
import com.bhvote.auth.dto.RegisterDto;
import com.bhvote.auth.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-05-08 15:43:24
 */
public interface UserService extends IService<User> {

    void register(RegisterDto registerDto);


    String login(LoginDto loginDto);

    void logout();

}

