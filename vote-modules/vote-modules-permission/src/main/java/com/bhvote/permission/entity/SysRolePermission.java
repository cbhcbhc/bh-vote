package com.bhvote.permission.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 角色权限关联表(SysRolePermission)表实体类
 *
 * @author makejava
 * @since 2023-05-09 09:11:11
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_permission")
public class SysRolePermission  {
    @TableId
    private Integer id;

    //角色ID
    private Long roleId;
    //权限ID
    private Long permissionId;



}

