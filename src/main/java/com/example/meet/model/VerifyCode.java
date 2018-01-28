package com.example.meet.model;

import lombok.Data;

import java.util.Date;

@Data
public class VerifyCode {
    private Long id;

    private String tel;

    private String verifyCode;

    private Integer used;

    private Date codeExpries;

    private Date createTime;

}