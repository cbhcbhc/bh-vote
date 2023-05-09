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
 * 投票表(VotePoll)表实体类
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("vote_poll")
public class VotePoll  {
    //投票ID
    @TableId
    private Long voteId;

    //投票标题
    private String voteTitle;
    //投票描述
    private String voteDescription;
    //投票图片
    private String voteImage;
    //创建者ID
    private Long voteCreatorId;
    //投票类型：0代表单选，1代表可多选
    private Integer voteType;
    //排序标志（数字越小越靠前）
    private Integer sort;
    //开始时间
    private LocalDateTime voteStartTime;
    //结束时间
    private LocalDateTime voteEndTime;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}

