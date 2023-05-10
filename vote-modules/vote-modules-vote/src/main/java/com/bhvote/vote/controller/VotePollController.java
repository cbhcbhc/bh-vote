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
     */
    @PostMapping("/vote/list")
    public R getVoteList(@RequestBody @Valid VoteListDto voteListDto){
        VoteListVo page = votePollService.getVoteList(voteListDto);
        return R.ok().put("data",page).put("msg","投票列表获取成功");
    }

    /**
     * 3. 获取单个投票详情接口
     * url: /vote/votepoll/voteInfo
     */
    @PostMapping("/voteInfo")
    public R getVoteInfoByVoteId(@RequestBody @Valid VoteInfoDto voteInfoDto){
        VoteVo voteVo = votePollService.getVoteInfoByVoteId(voteInfoDto);
        return R.ok().put("data",voteVo).put("msg","获取投票信息成功");
    }

}
