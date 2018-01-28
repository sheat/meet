package com.example.meet.controller;

import com.example.meet.dao.ServiceTypeMapper;
import com.example.meet.model.ServiceType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/serviceType")
public class ServiceTypeController {

    @Resource
    private ServiceTypeMapper serviceTypeMapper;

    @RequestMapping(value = "/")
    @ResponseBody
    public Object getAllServiceType() {
        List<ServiceType> lily = serviceTypeMapper.selectAll();
        return lily;
    }
}
