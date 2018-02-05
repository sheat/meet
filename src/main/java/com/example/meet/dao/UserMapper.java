package com.example.meet.dao;

import com.example.meet.model.MeetUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MeetUser record);

    int insertSelective(MeetUser record);

    MeetUser selectByPrimaryKey(Long id);

    MeetUser selectByPhone(String telephone);

    int updateByPrimaryKeySelective(MeetUser record);

    int updateByPrimaryKey(MeetUser record);

    @Select("SELECT * FROM user")
    List<MeetUser> selectAll();

}