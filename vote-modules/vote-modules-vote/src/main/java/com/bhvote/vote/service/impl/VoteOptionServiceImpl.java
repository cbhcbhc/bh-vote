package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.entity.VoteOption;
import com.bhvote.vote.mapper.VoteOptionMapper;
import com.bhvote.vote.service.VoteOptionService;
import org.springframework.stereotype.Service;

/**
 * 投票选项表(VoteOption)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:00:33
 */
@Service("voteOptionService")
public class VoteOptionServiceImpl extends ServiceImpl<VoteOptionMapper, VoteOption> implements VoteOptionService {

}

