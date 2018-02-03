package com.example.meet.controller;

import com.example.meet.dao.UserMapper;
import com.example.meet.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/")
    @ResponseBody
    public Object getAllUser() {
        User user = userMapper.selectByPhone("18801064494");
        return user;
    }
}
