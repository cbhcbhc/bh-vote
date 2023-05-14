package com.bhvote.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bhvote.permission.entity.SysPermission;
import com.bhvote.permission.entity.SysUserRole;

import java.util.List;


/**
 * 用户角色关联表(SysUserRole)表服务接口
 *
 * @author makejava
 * @since 2023-05-09 09:11:28
 */
public interface SysUserRoleService extends IService<SysUserRole> {


    void authorizeByUserId(Long userId);

    List<SysPermission> getPermissionByRoleId(Long roleId);

    List<SysPermission> getPermissionInfo(List<Long> permissions);
}

