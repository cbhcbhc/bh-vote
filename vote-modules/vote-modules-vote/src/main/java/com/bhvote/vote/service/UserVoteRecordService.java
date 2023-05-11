package com.bhvote.vote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bhvote.vote.dto.VoteJoinDto;
import com.bhvote.vote.entity.UserVoteRecord;


/**
 * 用户投票记录表(UserVoteRecord)表服务接口
 *
 * @author makejava
 * @since 2023-05-08 16:15:49
 */
public interface UserVoteRecordService extends IService<UserVoteRecord> {

    void updateRecord(VoteJoinDto dto);
}

