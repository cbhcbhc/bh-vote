package com.bhvote.vote.controller;

import com.bhvote.database.utils.PageUtils;
import com.bhvote.vote.dto.VoteCreateDto;
import com.bhvote.vote.service.VotePollService;
import domain.R;
import dto.PageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return
     */
    @PostMapping("/vote/create")
    public R voteCreate(VoteCreateDto voteCreateDto){
        votePollService.createVote(voteCreateDto);
        return R.ok().put("msg","投票创建成功！");
    }

    /**
     * 2. 分页获取投票列表
     * url: /vote/votepoll/vote/list
     */
    @GetMapping("/vote/list")
    public R getVoteList(@Valid PageDto pageDto){
        PageUtils page = votePollService.getVoteList(pageDto);
        return R.ok().put("data",page).put("msg","投票列表获取成功");
    }

}
