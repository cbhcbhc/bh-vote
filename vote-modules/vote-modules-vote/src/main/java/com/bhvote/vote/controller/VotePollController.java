package com.bhvote.vote.controller;

import com.bhvote.vote.dto.VoteCreateDto;
import com.bhvote.vote.service.VotePollService;
import domain.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public R vote(VoteCreateDto voteCreateDto){
        votePollService.createVote(voteCreateDto);
        return R.ok().put("msg","投票创建成功！");
    }
}
