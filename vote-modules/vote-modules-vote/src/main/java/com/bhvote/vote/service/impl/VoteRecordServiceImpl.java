package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.entity.VoteRecord;
import com.bhvote.vote.mapper.VoteRecordMapper;
import com.bhvote.vote.service.VoteRecordService;
import org.springframework.stereotype.Service;

/**
 * 投票记录表(VoteRecord)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
@Service("voteRecordService")
public class VoteRecordServiceImpl extends ServiceImpl<VoteRecordMapper, VoteRecord> implements VoteRecordService {

}

