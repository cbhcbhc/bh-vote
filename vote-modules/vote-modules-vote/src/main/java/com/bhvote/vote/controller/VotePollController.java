package com.bhvote.vote.controller;

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

    @PostMapping("/vote")
    public R vote(){
        return null;
    }
}