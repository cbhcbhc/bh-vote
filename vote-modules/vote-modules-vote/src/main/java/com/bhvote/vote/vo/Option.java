package com.bhvote.vote.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    //选项ID
    private Long optionId;
    //投票ID
    private Long voteId;
    //选项内容
    private String optionContent;
    //选项图片
    private String optionImage;
    //选项投票数
    private Integer voteCount;
}
