package com.bhvote.permission.controller;

import com.bhvote.permission.entity.SysPermission;
import com.bhvote.permission.service.SysUserRoleService;
import domain.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 2. 查询角色拥有的权限
     * url: /role/permission/permission/{roleId}
     */
    @GetMapping("/permission/{roleId}")
    public R getPermissionByRoleId(@PathVariable("roleId") Long roleId){
       List<SysPermission> sysPermissions =  sysUserRoleService.getPermissionByRoleId(roleId);
        return R.ok().put("data",sysPermissions).put("msg","查询角色权限成功");
    }
}
