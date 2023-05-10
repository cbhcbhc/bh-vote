package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.entity.VoteCategory;
import com.bhvote.vote.mapper.VoteCategoryMapper;
import com.bhvote.vote.service.VoteCategoryService;
import com.bhvote.vote.vo.VoteCategoryVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (VoteCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-05-09 22:34:57
 */
@Service("voteCategoryService")
public class VoteCategoryServiceImpl extends ServiceImpl<VoteCategoryMapper, VoteCategory> implements VoteCategoryService {

    @Override
    public List<VoteCategoryVo> getCategoryList() {
        List<VoteCategory> voteCategoryList = list();

        List<VoteCategoryVo> voteCategoryVoList = voteCategoryList.stream()
                .map(voteCategory -> {

                    VoteCategoryVo voteCategoryVo = new VoteCategoryVo();
                    voteCategoryVo.setCategoryId(voteCategory.getCategoryId());
                    voteCategoryVo.setCategoryName(voteCategory.getCategoryName());
                    voteCategoryVo.setCategoryIcon(voteCategory.getCategoryIcon());
                    voteCategoryVo.setCategoryDescription(voteCategory.getCategoryDescription());

                    return voteCategoryVo;
                }).collect(Collectors.toList());

        return voteCategoryVoList;
    }
}

