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
 * 投票结果表(VoteResult)表实体类
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("vote_result")
public class VoteResult  {
    //结果ID
    @TableId
    private Long resultId;

    //投票ID
    private Long voteId;
    //选项ID
    private Long optionId;
    //选项投票数
    private Integer voteCount;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;



}

