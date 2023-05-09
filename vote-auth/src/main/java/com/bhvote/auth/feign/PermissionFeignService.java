package com.bhvote.auth.feign;

import domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("vote-modules-permission")
public interface PermissionFeignService {
    /**
     * 1. 注册时给用户添加普通用户角色
     * url: /permission/role/{userId}
     */
    @PostMapping("/permission/role/{userId}")
    public R authorizeByUserId(@PathVariable("userId") Long userId);
}
