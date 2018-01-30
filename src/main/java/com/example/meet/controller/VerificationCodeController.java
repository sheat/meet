package com.example.meet.controller;

import com.example.meet.dao.VerifyCodeMapper;
import com.example.meet.model.VerifyCode;
import com.example.meet.utils.JsonResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * 登录及验证码
 *
 * @author Alexander Chou
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

    /**
     * 注册或登录
     */
    @RequestMapping(value = "/login/{phone}")
    @ResponseBody
    public Object getVerificationCode(@PathVariable String phone, String verificationCode) {
        JsonResponse jsonResponse = new JsonResponse();
        VerifyCode verifyCode = verifyCodeMapper.selectVerifyCodeByPhone(phone);

        if (null == verifyCode) {
            jsonResponse.setFailed("请点击发送验证码");
        } else if (!verificationCode.equals(verifyCode.getVerifyCode())) {
            jsonResponse.setFailed("验证码错误");
        } else if (VerifyCode.CODE_USED.equals(verifyCode.getUsed())) {
            jsonResponse.setFailed("验证码已使用，请重新发送");
        } else if (new Date().after(verifyCode.getCodeExpries())) {
            jsonResponse.setFailed("验证码已过期，请重新发送");
        } else {
            jsonResponse.setSuccessed(getToken());
        }

        return jsonResponse;
    }

    private String getToken() {
        return "";
    }

}
