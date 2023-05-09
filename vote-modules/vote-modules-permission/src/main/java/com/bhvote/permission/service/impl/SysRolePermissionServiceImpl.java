package com.bhvote.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.permission.entity.SysRolePermission;
import com.bhvote.permission.mapper.SysRolePermissionMapper;
import com.bhvote.permission.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色权限关联表(SysRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2023-05-09 09:11:11
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

}

