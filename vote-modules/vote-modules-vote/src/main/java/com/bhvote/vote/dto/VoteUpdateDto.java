package com.bhvote.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteUpdateDto {
    //投票id
    private Long voteId;
    //投票标题
    private String voteTitle;
    //投票描述
    private String voteDescription;
    //投票图片
    private String voteImage;
    //投票类型：0代表单选，1代表可多选
    private Integer voteType;
    //开始时间
    private LocalDateTime voteStartTime;
    //结束时间
    private LocalDateTime voteEndTime;

    //选项
    private List<OptionUpdate> options;

}
