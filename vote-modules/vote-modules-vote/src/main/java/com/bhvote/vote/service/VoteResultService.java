package com.bhvote.vote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bhvote.vote.entity.VoteResult;


/**
 * 投票结果表(VoteResult)表服务接口
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
public interface VoteResultService extends IService<VoteResult> {


    Integer getOptionCount(Long voteId, Long optionId);

    void removeByVoteId(Long voteId);
}

