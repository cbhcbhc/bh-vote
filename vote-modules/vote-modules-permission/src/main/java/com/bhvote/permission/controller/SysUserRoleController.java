package com.bhvote.permission.controller;

import com.bhvote.permission.service.SysUserRoleService;
import domain.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/permission")
public class SysUserRoleController {
    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 1. 注册时给用户添加普通用户角色
     * url: /role/permission/role/{userId}
     */
    @PostMapping("/role/{userId}")
    public R authorizeByUserId(@PathVariable("userId") Long userId){
        sysUserRoleService.authorizeByUserId(userId);
        return R.ok().put("msg","给用户授权成功");
    }
}
