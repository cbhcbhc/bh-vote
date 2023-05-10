package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.database.utils.PageResult;
import com.bhvote.database.utils.PageUtils;
import com.bhvote.vote.dto.VoteCreateDto;
import com.bhvote.vote.dto.VoteInfoDto;
import com.bhvote.vote.dto.VoteListDto;
import com.bhvote.vote.entity.*;
import com.bhvote.vote.feign.AuthFeignService;
import com.bhvote.vote.feign.feignvo.User;
import com.bhvote.vote.mapper.VotePollMapper;
import com.bhvote.vote.service.*;
import com.bhvote.vote.vo.Option;
import com.bhvote.vote.vo.Vote;
import com.bhvote.vote.vo.VoteListVo;
import com.bhvote.vote.vo.VoteVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 投票表(VotePoll)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
@Service("votePollService")
@Slf4j
public class VotePollServiceImpl extends ServiceImpl<VotePollMapper, VotePoll> implements VotePollService {
    @Resource
    private VoteCategoryService voteCategoryService;
    @Resource
    private VoteOptionService voteOptionService;
    @Resource
    private UserVoteRecordService userVoteRecordService;
    @Resource
    private VoteRecordService voteRecordService;
    @Resource
    private VoteResultService voteResultService;
    @Resource
    private AuthFeignService authFeignService;


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
    public VoteListVo getVoteList(VoteListDto voteListDto) {
        /**
         * 1. 获取分类下所有投票id --> VoteCategory
         * 2. 获取投票信息 --> vote_poll
         */


        //1. 获取分类下所有投票id --> VoteCategory
        Long categoryId = voteListDto.getCategoryId();
        LambdaQueryWrapper<VoteCategory> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(VoteCategory::getCategoryId, categoryId);
        List<VoteCategory> categoryList = voteCategoryService.list(categoryLambdaQueryWrapper);
        log.info("分类信息查询成功");

        //获取分类下所有的投票id
        List<Long> voteIds = categoryList.stream()
                .map(voteCategory -> voteCategory.getVoteId())
                .collect(Collectors.toList());

        //2. 获取投票信息
        List<VotePoll> votePolls = listByIds(voteIds);
        List<Vote> voteList = votePolls.stream()
                .map(votePoll -> {
                    //返回信息
                    Vote vote = new Vote();

                    //远程调用，查询创建投票人姓名 -->user
                    User userInfo = authFeignService.getUserInfo(votePoll.getVoteCreatorId());
                    String userName = userInfo.getUserName();

                    //设置返回信息
                    vote.setVoteId(votePoll.getVoteId());
                    vote.setVoteTitle(votePoll.getVoteTitle());
                    vote.setVoteDescription(votePoll.getVoteDescription());
                    vote.setVoteImage(votePoll.getVoteImage());
                    vote.setVoteNumber(votePoll.getVoteNumber());
                    vote.setVoteCreatorId(votePoll.getVoteCreatorId());
                    vote.setVoteCreateName(userName);
                    vote.setVoteStartTime(votePoll.getVoteStartTime());
                    vote.setVoteEndTime(votePoll.getVoteEndTime());

                    //返回投票信息
                    return vote;

                }).collect(Collectors.toList());
        log.info("投票信息设置成功");

        //分页
        PageResult<Vote> votePageResult = PageUtils.page(voteListDto.getPageNum(), voteListDto.getPageSize(), voteList);
        log.info("信息分页成功");
        VoteListVo voteListVo = new VoteListVo();
        voteListVo.setCategoryId(voteListDto.getCategoryId());
        voteListVo.setVote(votePageResult);

        return voteListVo;
    }

    @Override
    public VoteVo getVoteInfoByVoteId(VoteInfoDto voteInfoDto) {
        VoteVo voteVo = new VoteVo();

        Long voteId = voteInfoDto.getVoteId();

        //1.获取投票基本信息 --> vote_poll
        VotePoll votePoll = getById(voteId);
        log.info("投票基本信息查询成功");

        voteVo.setVoteId(votePoll.getVoteId());
        voteVo.setVoteTitle(votePoll.getVoteTitle());
        voteVo.setVoteDescription(votePoll.getVoteDescription());
        voteVo.setVoteImage(votePoll.getVoteImage());
        voteVo.setVoteNumber(votePoll.getVoteNumber());
        voteVo.setVoteCreatorId(votePoll.getVoteCreatorId());
        voteVo.setVoteStartTime(votePoll.getVoteStartTime());
        voteVo.setVoteEndTime(votePoll.getVoteEndTime());

        //2. 获取投票选项信息 --> vote_option
        List<VoteOption> voteOptionList = voteOptionService.getOptionByVoteId(voteId);
        log.info("投票选项信息查询成功");
        List<Option> optionList = voteOptionList.stream()
                .map(voteOption -> {
                    Option option = new Option();
                    Long optionId = voteOption.getOptionId();
                    option.setOptionId(optionId);
                    option.setVoteId(voteId);
                    option.setOptionContent(voteOption.getOptionContent());
                    option.setOptionImage(voteOption.getOptionImage());

                    //3. 获取选项票数 --> vote_result
                    Integer voteCount = voteResultService.getOptionCount(voteId, optionId);
                    log.info("选项票数查询成功");
                    option.setVoteCount(voteCount);

                    return option;
                }).collect(Collectors.toList());

        PageResult<Option> page = PageUtils.page(voteInfoDto.getPageNum(), voteInfoDto.getPageSize(), optionList);
        log.info("分页设置成功");
        //设置选项
        voteVo.setOptionList(page);

        return voteVo;
    }
}

