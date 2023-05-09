package com.bhvote.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.permission.entity.SysRole;
import com.bhvote.permission.mapper.SysRoleMapper;
import com.bhvote.permission.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2023-05-09 09:10:54
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}

