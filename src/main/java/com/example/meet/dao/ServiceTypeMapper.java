package com.example.meet.dao;

import com.example.meet.model.ServiceType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ServiceType record);

    int insertSelective(ServiceType record);

    ServiceType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ServiceType record);

    int updateByPrimaryKey(ServiceType record);

    @Select("SELECT id,service_name serviceName,category_id categoryId,category_name categoryName,image_url_default imageUrlDefault,iamge_url_selected iamgeUrlSelected,in_home_page inHomePage,order_num orderNum,usable,createtime,updatetime FROM service_type")
    List<ServiceType> selectAll();
}