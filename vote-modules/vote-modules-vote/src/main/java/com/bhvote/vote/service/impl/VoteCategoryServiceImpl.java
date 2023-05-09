package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.entity.VoteCategory;
import com.bhvote.vote.mapper.VoteCategoryMapper;
import com.bhvote.vote.service.VoteCategoryService;
import org.springframework.stereotype.Service;

/**
 * (VoteCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-05-09 22:34:57
 */
@Service("voteCategoryService")
public class VoteCategoryServiceImpl extends ServiceImpl<VoteCategoryMapper, VoteCategory> implements VoteCategoryService {

}

