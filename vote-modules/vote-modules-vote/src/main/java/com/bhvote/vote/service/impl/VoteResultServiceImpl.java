package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.entity.VoteResult;
import com.bhvote.vote.mapper.VoteResultMapper;
import com.bhvote.vote.service.VoteResultService;
import org.springframework.stereotype.Service;

/**
 * 投票结果表(VoteResult)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
@Service("voteResultService")
public class VoteResultServiceImpl extends ServiceImpl<VoteResultMapper, VoteResult> implements VoteResultService {

}

