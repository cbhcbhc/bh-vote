package com.bhvote.vote.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户投票记录表(Uservoterecord)表实体类
 *
 * @author makejava
 * @since 2023-05-08 16:15:48
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_vote_record")
public class UserVoteRecord {
    //记录ID
    @TableId
    private Long id;

    //用户ID
    private Long userId;
    //投票ID
    private Long voteId;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;


}
