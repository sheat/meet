package com.example.meet.model;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceInstance {
    private Long id;

    private Long meetUserId;

    private Long serviceTypeId;

    private String coverimgUrl;

    private String covervideoUrl;

    private Integer covervideoDuration;

    private String audioUrl;

    private Integer audioDuration;

    private Integer meetUnit;

    private Integer price;

    private Integer meetMode;

    private Integer meetWay;

    private String serviceDescription;

    private Integer score;

    private Integer dealCount;

    private Integer commentCount;

    private Integer upvoteCount;

    private Integer verifyStatus;

    private Date createTime;

    private Date updateTime;

    private Integer usable;

}