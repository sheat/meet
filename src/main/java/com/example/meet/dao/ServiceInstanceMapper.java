package com.example.meet.dao;

import com.example.meet.model.ServiceInstance;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceInstanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ServiceInstance record);

    int insertSelective(ServiceInstance record);

    ServiceInstance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ServiceInstance record);

    int updateByPrimaryKey(ServiceInstance record);
}