package com.example.meet.controller;

import com.example.meet.dao.MeetUserMapper;
import com.example.meet.model.MeetUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private MeetUserMapper meetUserMapper;

    @RequestMapping(value = "/")
    @ResponseBody
    public Object getAllUser() {
        MeetUser meetUser = meetUserMapper.selectByPhone("18801064494");
        return meetUser;
    }
}
