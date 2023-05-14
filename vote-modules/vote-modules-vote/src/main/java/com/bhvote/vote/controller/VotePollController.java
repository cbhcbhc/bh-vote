package com.bhvote.vote.controller;

import com.bhvote.vote.dto.*;
import com.bhvote.vote.service.VotePollService;
import com.bhvote.vote.vo.VoteListVo;
import com.bhvote.vote.vo.VoteResultVo;
import com.bhvote.vote.vo.VoteVo;
import domain.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
     * url: /vote/votepoll/join/
     * 用户参与投票
     */
    @PostMapping("/join")
    public R joinVote(@RequestBody @Valid VoteJoinDto dto){
        votePollService.joinVote(dto);
        return R.ok().put("msg","用户投票成功");
    }

    /**
     * 5. 根据voteId获取所有投票信息
     * url: /vote/votepoll/info/{voteId}
     * 排行榜
     */
    @GetMapping("/info/{voteId}")
    public R getVoteInfo(@PathVariable("voteId") Long voteId){
        List<VoteResultVo> vo = votePollService.getVoteInfo(voteId);
        return R.ok().put("data",vo).put("msg","投票信息获取成功");
    }

    /**
     * 6. 删除投票
     * url: /vote/votepoll/delete/{voteId}
     */
    @DeleteMapping("/delete/{voteId}")
    public R deleteVote(@PathVariable("voteId") Long voteId){
        votePollService.deleteVote(voteId);
        return R.ok().put("msg","投票删除成功");
    }

    /**
     * 7. 修改投票
     * url: /vote/votepoll/update
     */
    @PostMapping("/update")
    public R updateVote(@RequestBody VoteUpdateDto dto){
        votePollService.updateVote(dto);
        return R.ok().put("msg","修改投票成功");
    }

}
