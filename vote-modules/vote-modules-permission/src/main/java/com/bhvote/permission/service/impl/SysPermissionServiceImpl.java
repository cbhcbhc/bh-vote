package com.bhvote.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.permission.entity.SysPermission;
import com.bhvote.permission.mapper.SysPermissionMapper;
import com.bhvote.permission.service.SysPermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限表(SysPermission)表服务实现类
 *
 * @author makejava
 * @since 2023-05-09 09:10:39
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

}

