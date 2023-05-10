package com.bhvote.vote.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    //投票ID
    private Long voteId;
    //投票标题
    private String voteTitle;
    //投票描述
    private String voteDescription;
    //投票图片
    private String voteImage;
    //投票总人数
    private Integer voteNumber;
    //创建者ID
    private Long voteCreatorId;
    //创建者姓名
    private String voteCreateName;
    //开始时间
    private LocalDateTime voteStartTime;
    //结束时间
    private LocalDateTime voteEndTime;
}
