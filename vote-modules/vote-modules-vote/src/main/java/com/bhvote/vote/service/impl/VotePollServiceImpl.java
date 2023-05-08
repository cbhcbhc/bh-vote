package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.entity.VotePoll;
import com.bhvote.vote.mapper.VotePollMapper;
import com.bhvote.vote.service.VotePollService;
import org.springframework.stereotype.Service;

/**
 * 投票表(VotePoll)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
@Service("votePollService")
public class VotePollServiceImpl extends ServiceImpl<VotePollMapper, VotePoll> implements VotePollService {

}

