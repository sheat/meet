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
    public String getAllUser() {
        List<User> lily = userMapper.selectAll();
        if(null !=lily&& lily.size()>0){
            return lily.get(0).getTelephone();
        }
        return "";
    }
}
