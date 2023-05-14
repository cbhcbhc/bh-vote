package com.bhvote.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bhvote.permission.entity.SysRolePermission;

import java.util.List;


/**
 * 角色权限关联表(SysRolePermission)表服务接口
 *
 * @author makejava
 * @since 2023-05-09 09:11:11
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    List<Long> getPermissionIds(Long roleId);
}

