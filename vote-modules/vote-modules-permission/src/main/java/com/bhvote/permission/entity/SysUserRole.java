package com.bhvote.permission.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户角色关联表(SysUserRole)表实体类
 *
 * @author makejava
 * @since 2023-05-09 09:11:28
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class SysUserRole  {
    @TableId
    private Integer id;

    //用户ID
    private Long userId;
    //角色ID
    private Long roleId;



}

