package com.example.meet.dao;

import com.example.meet.model.VerifyCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyCodeMapper {
    int deleteByPrimaryKey(Long id);

    @Insert("INSERT INTO verify_code(tel,verify_code,used,code_expires,create_time) VALUES(#{tel},#{verifyCode},0,date_add(NOW(), interval 30 minute),NOW())")
    int insert(VerifyCode record);

    int insertSelective(VerifyCode record);

    VerifyCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VerifyCode record);

    @Update("update verify_code set verify_code = #{verifyCode},code_expires = date_add(NOW(), interval 30 minute) where tel = #{tel}")
    int updateByTel(VerifyCode record);

    @Select("select count(*) from verify_code where tel = #{phone}")
    Integer selectCountByPhone(String phone);


}