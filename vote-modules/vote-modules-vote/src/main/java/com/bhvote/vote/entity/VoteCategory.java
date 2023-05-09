package com.bhvote.vote.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (VoteCategory)表实体类
 *
 * @author makejava
 * @since 2023-05-09 22:34:55
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("vote_category")
public class VoteCategory  {
    //投票分类id
    @TableId
    private Long categoryId;

    //投票分类名字
    private String categoryName;
    //投票分类图标
    private String categoryIcon;
    //投票分类描述
    private String categoryDescription;
    //投票id
    private Long voteId;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;



}

