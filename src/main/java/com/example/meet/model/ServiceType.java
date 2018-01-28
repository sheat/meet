package com.example.meet.model;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceType {
    private Long id;

    private String serviceName;

    private Long categoryId;

    private String categoryName;

    private String imageUrlDefault;

    private String iamgeUrlSelected;

    private Integer inHomePage;

    private Integer orderNum;

    private Integer usable;

    private Date createtime;

    private Date updatetime;

}