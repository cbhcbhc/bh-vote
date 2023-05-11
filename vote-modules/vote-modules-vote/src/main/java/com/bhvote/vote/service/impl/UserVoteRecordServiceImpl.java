package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.dto.VoteJoinDto;
import com.bhvote.vote.entity.UserVoteRecord;
import com.bhvote.vote.mapper.UservoterecordMapper;
import com.bhvote.vote.service.UserVoteRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 用户投票记录表(Uservoterecord)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:15:49
 */
@Service("uservoterecordService")
public class UserVoteRecordServiceImpl extends ServiceImpl<UservoterecordMapper, UserVoteRecord> implements UserVoteRecordService {

    @Override
    public void updateRecord(VoteJoinDto dto) {
        UserVoteRecord userVoteRecord = new UserVoteRecord();
        userVoteRecord.setUserId(dto.getUserId());
        userVoteRecord.setVoteId(dto.getVoteId());
        userVoteRecord.setCreateTime(LocalDateTime.now());
        userVoteRecord.setUpdateTime(LocalDateTime.now());

        save(userVoteRecord);


    }
}

