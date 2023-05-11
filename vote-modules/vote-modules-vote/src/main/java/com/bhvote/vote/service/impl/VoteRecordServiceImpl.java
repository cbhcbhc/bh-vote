package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.dto.VoteJoinDto;
import com.bhvote.vote.entity.VoteRecord;
import com.bhvote.vote.entity.VoteResult;
import com.bhvote.vote.mapper.VoteRecordMapper;
import com.bhvote.vote.service.VoteRecordService;
import com.bhvote.vote.service.VoteResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 投票记录表(VoteRecord)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:00:34
 */
@Service("voteRecordService")
@Slf4j
public class VoteRecordServiceImpl extends ServiceImpl<VoteRecordMapper, VoteRecord> implements VoteRecordService {
    @Resource
    VoteResultService voteResultService;
    @Override
    public void updateRecord(VoteJoinDto dto) {
        VoteRecord voteRecord = new VoteRecord();
        voteRecord.setVoteId(dto.getVoteId());
        voteRecord.setUserId(dto.getUserId());
        voteRecord.setSelectedOptionId(dto.getOptionId());
        voteRecord.setCreateTime(LocalDateTime.now());
        voteRecord.setUpdateTime(LocalDateTime.now());

        //更新投票记录表  --> vote_record
        save(voteRecord);

        //更新投票结果表  --> vote_result
        VoteResult voteResult = new VoteResult();
        voteResult.setVoteId(dto.getVoteId());
        voteResult.setOptionId(dto.getOptionId());
        voteResult.setVoteCount(voteResult.getVoteCount() + 1);
        voteResult.setCreateTime(LocalDateTime.now());
        voteResult.setUpdateTime(LocalDateTime.now());

        LambdaQueryWrapper<VoteResult> w = new LambdaQueryWrapper<>();
        w.eq(VoteResult::getVoteId,dto.getVoteId());
        w.eq(VoteResult::getOptionId,dto.getOptionId());

        //1.查询投票结果是否存在
        if ( null == voteResultService.getBaseMapper().selectOne(w)){
            log.info("投票结果选项不存在，进行插入操作");
            //插入
            voteResultService.save(voteResult);
        }
        //更新
        voteResultService.update(voteResult,w);
        log.info("投票选项数结果更新成功");


    }
}

