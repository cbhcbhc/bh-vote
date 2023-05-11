package com.bhvote.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteJoinDto {
    //用户id
    @NotNull(message = "用户id不能为空")
    private Long userId;
    //投票id
    @NotNull(message = "投票id不能为空")
    private Long voteId;
    //选项id
    @NotNull(message = "选项id不能为空")
    private Long optionId;

}
