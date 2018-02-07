package com.example.meet.controller;

import com.example.meet.dao.MeetUserMapper;
import com.example.meet.model.MeetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private MeetUserMapper meetUserMapper;

    @RequestMapping(value = "/")
    @ResponseBody
    public Object getAllUser(HttpServletRequest request) {
        String currentTelephone = (String)request.getAttribute("currentTelephone");
//        MeetUser meetUser = meetUserMapper.selectByPhone("18801064494");
        return currentTelephone;
    }
}
