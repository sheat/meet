package com.example.meet.controller;

import com.example.meet.dao.ServiceInstanceMapper;
import com.example.meet.model.ServiceInstance;
import com.example.meet.model.ServiceType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务实例
 *
 * @author Alex
 * Date: 2018-02-08
 * Time: 16:09
 */
@RestController
@RequestMapping("/serviceInstance")
public class ServiceInstanceController {

    @Resource
    private ServiceInstanceMapper serviceInstanceMapper;

    /**
     * 新增服务实例
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllServiceType() {
        List<ServiceType> lily = serviceTypeMapper.selectAll();
        return lily;
    }
}
