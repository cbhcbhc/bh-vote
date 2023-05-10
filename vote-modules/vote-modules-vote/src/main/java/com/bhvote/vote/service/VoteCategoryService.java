package com.bhvote.vote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bhvote.vote.entity.VoteCategory;
import com.bhvote.vote.vo.VoteCategoryVo;

import java.util.List;


/**
 * (VoteCategory)表服务接口
 *
 * @author makejava
 * @since 2023-05-09 22:34:56
 */
public interface VoteCategoryService extends IService<VoteCategory> {

    List<VoteCategoryVo> getCategoryList();

}

