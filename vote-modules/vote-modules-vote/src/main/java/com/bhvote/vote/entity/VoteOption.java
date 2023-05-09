package com.bhvote.vote.entity;

import java.time.LocalDateTime;
import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 投票选项表(VoteOption)表实体类
 *
 * @author makejava
 * @since 2023-05-08 16:00:32
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("vote_option")
public class VoteOption  {
    //选项ID
    @TableId
    private Long optionId;

    //投票ID
    private Integer voteId;
    //选项内容
    private String optionContent;
    //选项图片
    private String optionImage;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;



}

