package com.example.meet.controller;

import com.example.meet.dao.UserMapper;
import com.example.meet.dao.VerifyCodeMapper;
import com.example.meet.model.MeetUser;
import com.example.meet.model.VerifyCode;
import com.example.meet.common.JsonResponse;
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

    @Resource
    private UserMapper userMapper;

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
    public Object signInOrSignUp(@PathVariable String telephone, String verificationCode) {
        JsonResponse jsonResponse = new JsonResponse();

        /* 查询验证码，不正确则返回错误信息 */
        VerifyCode verifyCode = verifyCodeMapper.selectVerifyCodeByPhone(telephone);
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

        /* 查询用户是否存在，如果存在则执行登录流程，否则执行注册流程 */
        MeetUser meetUser = userMapper.selectByPhone(telephone);
        if (null != meetUser) {
            meetUser = new MeetUser();
            userMapper.insert(meetUser);
        } else {

        }

        return jsonResponse;
    }

    private String getToken() {
        return "";
    }

}
