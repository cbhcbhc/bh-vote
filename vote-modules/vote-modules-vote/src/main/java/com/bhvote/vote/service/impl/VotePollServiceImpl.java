package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.database.utils.PageUtils;
import com.bhvote.vote.dto.VoteCreateDto;
import com.bhvote.vote.entity.*;
import com.bhvote.vote.mapper.VotePollMapper;
import com.bhvote.vote.service.*;
import dto.PageDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 投票表(VotePoll)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
@Service("votePollService")
public class VotePollServiceImpl extends ServiceImpl<VotePollMapper, VotePoll> implements VotePollService {
    @Resource
    private VoteCategoryService voteCategoryService;
    @Resource
    private VoteOptionService voteOptionService;
    @Resource
    private UserVoteRecordService userVoteRecordService;
    @Resource
    private VoteRecordService voteRecordService;


    @Transactional
    @Override
    public void createVote(VoteCreateDto voteCreateDto) {
        /**
         * 1. 保存--> vote_poll 投票表
         * 2. 保存--> vote_category 投票分类表
         * 3. 保存--> vote_option 投票选项表
         */

        //创建投票用户
        Long userId = voteCreateDto.getVoteCreatorId();

        //1. 保存--> vote_poll 投票表
        VotePoll votePoll = new VotePoll();
        votePoll.setVoteTitle(voteCreateDto.getVoteTitle());
        votePoll.setVoteDescription(voteCreateDto.getVoteDescription());
        votePoll.setVoteImage(votePoll.getVoteImage());
        votePoll.setVoteCreatorId(userId);
        votePoll.setVoteType(votePoll.getVoteType());
        votePoll.setVoteStartTime(voteCreateDto.getVoteStartTime());
        votePoll.setVoteEndTime(voteCreateDto.getVoteEndTime());
        votePoll.setCreateTime(LocalDateTime.now());
        votePoll.setUpdateTime(LocalDateTime.now());

        //保存
        save(votePoll);

        //投票id
        Long voteId = votePoll.getVoteId();

        //2. 保存--> vote_category 投票分类表
        VoteCategory voteCategory = new VoteCategory();
        voteCategory.setCategoryName(voteCreateDto.getCategoryName());
        voteCategory.setCategoryIcon(voteCreateDto.getCategoryIcon());
        voteCategory.setCategoryDescription(voteCreateDto.getCategoryDescription());
        voteCategory.setVoteId(voteId);
        voteCategory.setCreateTime(LocalDateTime.now());
        voteCategory.setUpdateTime(LocalDateTime.now());

        //保存
        voteCategoryService.save(voteCategory);

        //3. 保存--> vote_option 投票选项表
        VoteOption voteOption = new VoteOption();
        voteOption.setVoteId(voteId);
        voteOption.setOptionContent(voteCreateDto.getOptionContent());
        voteOption.setOptionImage(voteCreateDto.getOptionImage());
        voteOption.setCreateTime(LocalDateTime.now());
        voteOption.setUpdateTime(LocalDateTime.now());

        //保存
        voteOptionService.save(voteOption);


    }

    @Override
    public PageUtils getVoteList(PageDto pageDto) {
        return null;
    }
}

