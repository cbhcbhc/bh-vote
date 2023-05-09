package com.bhvote.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 创建投票
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteCreateDto {
    /**
     * vote_poll 投票表
     */
    //投票标题
    private String voteTitle;
    //投票描述
    private String voteDescription;
    //投票图片
    private String voteImage;
    //创建者ID
    private Long voteCreatorId;
    //投票类型：0代表单选，1代表可多选
    private Integer voteType = 0;
    //开始时间
    private LocalDateTime voteStartTime;
    //结束时间
    private LocalDateTime voteEndTime;

    /**
     * vote_category 投票分类表
     */
    //投票分类名字
    private String categoryName;
    //投票分类图标
    private String categoryIcon;
    //投票分类描述
    private String categoryDescription;

    /**
     * vote_option 投票选项表
     */
    //选项内容
    private String optionContent;
    //选项图片
    private String optionImage;
}
