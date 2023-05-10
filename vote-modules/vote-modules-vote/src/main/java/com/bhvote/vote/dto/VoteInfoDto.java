package com.bhvote.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteInfoDto {
    //投票分类id
    @NotNull(message = "投票id不能为空")
    private Long voteId;
    //分页参数
    @NotNull(message = "当前页不能为空")
    private Integer pageNum;
    @NotNull(message = "页码不能为空")
    private Integer pageSize;
}
