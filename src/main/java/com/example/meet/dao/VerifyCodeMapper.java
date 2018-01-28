package com.example.meet.dao;

import com.example.meet.model.VerifyCode;
import org.apache.ibatis.annotations.Insert;

public interface VerifyCodeMapper {
    int deleteByPrimaryKey(Long id);

    @Insert("INSERT INTO verify_code(tel,verify_code,used,create_time) VALUES(#{tel},#{verifyCode},0,NOW())")
    int insert(VerifyCode record);

    int insertSelective(VerifyCode record);

    VerifyCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VerifyCode record);

    int updateByPrimaryKey(VerifyCode record);
}