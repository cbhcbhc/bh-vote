package com.bhvote.vote.vo;


import com.bhvote.database.utils.PageResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteListVo {
    //投票分类Id
    private Long categoryId;
    //投票信息列表
    private PageResult<Vote> vote;

}
