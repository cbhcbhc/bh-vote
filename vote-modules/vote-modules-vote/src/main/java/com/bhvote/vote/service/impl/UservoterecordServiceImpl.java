package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.entity.UserVoteRecord;
import com.bhvote.vote.mapper.UservoterecordMapper;
import com.bhvote.vote.service.UservoterecordService;
import org.springframework.stereotype.Service;

/**
 * 用户投票记录表(Uservoterecord)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:15:49
 */
@Service("uservoterecordService")
public class UservoterecordServiceImpl extends ServiceImpl<UservoterecordMapper, UserVoteRecord> implements UservoterecordService {

}

