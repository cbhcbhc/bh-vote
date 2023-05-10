package com.bhvote.vote.feign;

import com.bhvote.vote.feign.feignvo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("vote-auth")
public interface AuthFeignService {
    /**
     * 1. 根据用户id查询用户信息
     * url: /auth/getUserInfo/{userId}
     */
    @GetMapping("/auth/getUserInfo/{userId}")
    public User getUserInfo(@PathVariable("userId") Long userId);
}
