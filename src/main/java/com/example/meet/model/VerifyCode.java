package com.example.meet.model;

import lombok.Data;

import java.util.Date;

@Data
public class VerifyCode {
    public static final Integer CODE_USED = 1;
    public static final Integer CODE_UNUSED = 0;

    private Long id;

    private String tel;

    private String verifyCode;

    private Integer used;

    private Date codeExpries;

    private Date createTime;

}