package com.bhvote.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionUpdate {
    //选项ID
    private Long optionId;
    //选项内容
    private String optionContent;
    //选项图片
    private String optionImage;
}
