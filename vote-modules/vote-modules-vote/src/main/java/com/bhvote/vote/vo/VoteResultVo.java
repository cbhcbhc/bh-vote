package com.bhvote.vote.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class VoteResultVo {
    //结果ID
    private Long resultId;
    //投票ID
    private Long voteId;
    //选项ID
    private Long optionId;
    //选项投票数
    private Integer voteCount;

}

