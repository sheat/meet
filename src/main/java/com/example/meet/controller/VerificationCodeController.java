package com.example.meet.controller;

import com.example.meet.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/verification")
public class VerificationCodeController {

    @RequestMapping(value = "/")
    @ResponseBody
    public String getAllUser() {
        return "";
    }
}
