package com.example.meet.controller;

import com.example.meet.dao.VerifyCodeMapper;
import com.example.meet.model.VerifyCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * 验证码
 */
@RestController
@RequestMapping("/verification")
public class VerificationCodeController {
    @Resource
    private VerifyCodeMapper verifyCodeMapper;

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/phone/{phone}")
    @ResponseBody
    public String getVerificationCode(@PathVariable String phone) {
        /* 生成6位随机数字 */
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }

        /* 如果验证码记录已存在，则更新，否则新增 */
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVerifyCode(result);
        verifyCode.setTel(phone);

        Integer count = verifyCodeMapper.selectCountByPhone(phone);

        if (count > 0) {
            verifyCodeMapper.updateByTel(verifyCode);
        } else {
            verifyCodeMapper.insert(verifyCode);
        }

        return result;
    }
}
