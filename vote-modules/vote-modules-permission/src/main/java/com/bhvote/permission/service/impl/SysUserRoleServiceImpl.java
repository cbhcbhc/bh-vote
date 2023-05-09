package com.bhvote.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.permission.constant.PermissionConstant;
import com.bhvote.permission.entity.SysUserRole;
import com.bhvote.permission.mapper.SysUserRoleMapper;
import com.bhvote.permission.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色关联表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-05-09 09:11:28
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public void authorizeByUserId(Long userId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(PermissionConstant.COMMON);
        //保存用户权限
        save(sysUserRole);

    }
}

