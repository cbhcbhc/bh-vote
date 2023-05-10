package com.bhvote.vote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bhvote.database.utils.PageUtils;
import com.bhvote.vote.dto.VoteCreateDto;
import com.bhvote.vote.entity.VotePoll;
import dto.PageDto;


/**
 * 投票表(VotePoll)表服务接口
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
public interface VotePollService extends IService<VotePoll> {

    void createVote(VoteCreateDto voteCreateDto);

    PageUtils getVoteList(PageDto pageDto);
}

