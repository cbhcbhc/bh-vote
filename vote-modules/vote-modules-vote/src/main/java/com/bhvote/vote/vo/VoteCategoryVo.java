package com.bhvote.vote.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteCategoryVo {
    //投票分类id
    private Long categoryId;

    //投票分类名字
    private String categoryName;
    //投票分类图标
    private String categoryIcon;
    //投票分类描述
    private String categoryDescription;
}
