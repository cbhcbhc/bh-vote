package com.bhvote.vote.controller;

import com.bhvote.vote.service.VoteCategoryService;
import com.bhvote.vote.vo.VoteCategoryVo;
import com.bhvote.vote.vo.VoteListVo;
import domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteCategoryController {
    @Resource
    private VoteCategoryService voteCategoryService;

    /**
     * 1.获取所有投票分类
     * url: /vote/vote/getVoteCategory/list
     */
    @GetMapping("/vote/getVoteCategory/list")
    public R getCategoryList(){
        List<VoteCategoryVo> voteListVos = voteCategoryService.getCategoryList();
        return R.ok().put("data",voteListVos).put("msg","投票分类获取成功");
    }


}
