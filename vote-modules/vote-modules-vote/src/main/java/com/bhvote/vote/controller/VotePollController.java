package com.bhvote.vote.controller;

import com.bhvote.vote.dto.VoteCreateDto;
import com.bhvote.vote.dto.VoteInfoDto;
import com.bhvote.vote.dto.VoteListDto;
import com.bhvote.vote.service.VotePollService;
import com.bhvote.vote.vo.VoteListVo;
import com.bhvote.vote.vo.VoteVo;
import domain.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/votepoll")
public class VotePollController {
    @Resource
    private VotePollService votePollService;

    /**
     * 1. 创建投票
     * url: /vote/votepoll/vote/create
     * 用于创建投票，包括投票标题、选项等信息
     * @return
     */
    @PostMapping("/vote/create")
    public R voteCreate(VoteCreateDto voteCreateDto){
        votePollService.createVote(voteCreateDto);
        return R.ok().put("msg","投票创建成功！");
    }

    /**
     * 2. 根据分类Id分页获取投票列表
     * url: /vote/votepoll/vote/list
     * 用于获取所有的投票列表
     */
    @PostMapping("/vote/list")
    public R getVoteList(@RequestBody @Valid VoteListDto voteListDto){
        VoteListVo page = votePollService.getVoteList(voteListDto);
        return R.ok().put("data",page).put("msg","投票列表获取成功");
    }

    /**
     * 3. 获取单个投票详情接口
     * url: /vote/votepoll/voteInfo
     * 获取单个投票详情接口：用于获取单个投票的详细信息，包括投票标题、选项、投票人数等信息
     */
    @PostMapping("/voteInfo")
    public R getVoteInfoByVoteId(@RequestBody @Valid VoteInfoDto voteInfoDto){
        VoteVo voteVo = votePollService.getVoteInfoByVoteId(voteInfoDto);
        return R.ok().put("data",voteVo).put("msg","获取投票信息成功");
    }

    /**
     * 4. 用户投票
     * url: /vote/votepoll/join/{userId}
     * 用户参与投票
     */
    @PostMapping("/join/{userId}")
    public R joinVote(@PathVariable("userId") Long userId){
        votePollService.joinVote(userId);
        return R.ok().put("msg","用户投票成功");
    }

}
