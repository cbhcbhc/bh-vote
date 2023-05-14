package com.bhvote.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.permission.constant.PermissionConstant;
import com.bhvote.permission.entity.SysPermission;
import com.bhvote.permission.entity.SysUserRole;
import com.bhvote.permission.mapper.SysUserRoleMapper;
import com.bhvote.permission.service.SysPermissionService;
import com.bhvote.permission.service.SysRolePermissionService;
import com.bhvote.permission.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色关联表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-05-09 09:11:28
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Resource
    private SysRolePermissionService sysRolePermissionService;
    @Resource
    private SysPermissionService sysPermissionService;
    @Override
    public void authorizeByUserId(Long userId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(PermissionConstant.COMMON);
        //保存用户权限
        save(sysUserRole);

    }

    @Override
    public List<SysPermission> getPermissionByRoleId(Long roleId) {
        /**
         * 1. 根据roleId查询出对应的permissionId --> sys_role_permission
         * 2. 根据permissionId查询出对应的权限信息 --> sys_permission
         */
        //1. 根据roleId查询出对应的permissionId --> sys_role_permission
        List<Long> permissions =  sysRolePermissionService.getPermissionIds(roleId);
        //2. 根据permissionId查询出对应的权限信息 --> sys_permission
        List<SysPermission> sysPermissions = this.getPermissionInfo(permissions);
        return sysPermissions;
    }
    @Override
    public List<SysPermission> getPermissionInfo(List<Long> permissions) {
        List<SysPermission> sysPermissions = permissions.stream()
                .map(perId -> {
                    LambdaQueryWrapper<SysPermission> w = new LambdaQueryWrapper<>();
                    w.eq(SysPermission::getPermissionId, perId);
                    SysPermission sysPermission = sysPermissionService.getBaseMapper().selectOne(w);

                    return sysPermission;
                }).collect(Collectors.toList());
        return sysPermissions;
    }
}

