package com.bhvote.vote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bhvote.vote.dto.VoteCreateDto;
import com.bhvote.vote.dto.VoteInfoDto;
import com.bhvote.vote.dto.VoteListDto;
import com.bhvote.vote.entity.VotePoll;
import com.bhvote.vote.vo.VoteListVo;
import com.bhvote.vote.vo.VoteVo;


/**
 * 投票表(VotePoll)表服务接口
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
public interface VotePollService extends IService<VotePoll> {

    void createVote(VoteCreateDto voteCreateDto);

    VoteListVo getVoteList(VoteListDto voteListDto);

    VoteVo getVoteInfoByVoteId( VoteInfoDto voteInfoDto);

    void joinVote(Long userId);
}

