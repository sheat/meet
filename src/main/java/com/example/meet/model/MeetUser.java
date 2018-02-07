package com.example.meet.model;

import lombok.Data;

import java.util.Date;

@Data
public class MeetUser {
    private Long id;

    private String telephone;

    private String wechatUnionId;

    private String wechatAppId;

    private String wechatOfficalAccountId;

    private String wechatMiniProgramsId;

    private String weiboId;

    private String weiboToken;

    private Date weiboTokenExpirationTime;

    private String nickname;

    private Integer sex;

    private Date birthDate;

    private String headImgUrl;

    private String description;

    private String province;

    private String city;

    private String location;

    private Integer realPersonVerified;

    private Integer realPersonVerifiedType;

    private String identityNumber;

    private String rpVerifiedPassportUrl;

    private Integer verifiedImg;

    private String verifiedImgUrl;

    private Integer verifiedVideo;

    private String verifiedVideoUrl;

    private Integer vipLevel;

    private Integer followerCount;

    private Integer followCount;

    private Integer hotCount;

    private Integer visitorCount;

    private Integer receiveOrderPercent;

    private Integer receiveOrderCount;

    private Integer moneyRmb;

    private Integer moneyMeet;

    private Integer moneyScore;

    private Long lat;

    private Long lng;

    private String macAddress;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    private Integer usable;

    private String meetPassword;

    private String meetToken;
}