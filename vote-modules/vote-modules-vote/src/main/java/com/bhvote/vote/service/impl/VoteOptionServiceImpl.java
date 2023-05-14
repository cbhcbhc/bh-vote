package com.bhvote.vote.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhvote.vote.dto.OptionUpdate;
import com.bhvote.vote.dto.VoteUpdateDto;
import com.bhvote.vote.entity.VoteOption;
import com.bhvote.vote.mapper.VoteOptionMapper;
import com.bhvote.vote.service.VoteOptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 投票选项表(VoteOption)表服务实现类
 *
 * @author makejava
 * @since 2023-05-08 16:00:33
 */
@Service("voteOptionService")
public class VoteOptionServiceImpl extends ServiceImpl<VoteOptionMapper, VoteOption> implements VoteOptionService {

    @Override
    public List<VoteOption> getOptionByVoteId(Long voteId) {
        LambdaQueryWrapper<VoteOption> w = new LambdaQueryWrapper<>();
        w.eq(VoteOption::getVoteId,voteId);

        List<VoteOption> list = list(w);
        return list;
    }

    @Override
    public void removeByVoteId(Long voteId) {
        LambdaQueryWrapper<VoteOption> w = new LambdaQueryWrapper<>();
        w.eq(VoteOption::getVoteId,voteId);

        remove(w);
    }

    @Override
    public void updateOption(VoteUpdateDto dto) {
        List<OptionUpdate> options = dto.getOptions();
        List<VoteOption> voteOptions = options.stream()
                .map(option -> {
                    VoteOption voteOption = new VoteOption();
                    voteOption.setOptionId(option.getOptionId());
                    voteOption.setVoteId(dto.getVoteId());
                    voteOption.setOptionContent(option.getOptionContent());
                    voteOption.setOptionImage(option.getOptionImage());

                    return voteOption;
                }).collect(Collectors.toList());

        voteOptions.forEach(option->{
            LambdaQueryWrapper<VoteOption> w = new LambdaQueryWrapper<>();
            w.eq(VoteOption::getVoteId,option.getVoteId());
            w.eq(VoteOption::getOptionId,option.getOptionId());

            update(option,w);
        });
    }


}

