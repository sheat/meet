package com.example.meet.controller;

import com.example.meet.dao.MeetUserMapper;
import com.example.meet.dao.VerifyCodeMapper;
import com.example.meet.filter.NeedNotLogin;
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
    private MeetUserMapper meetUserMapper;

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/phone/{phone}")
    @ResponseBody
    @NeedNotLogin
    public String getVerificationCode(@PathVariable String phone) {
        JsonResponse jsonResponse = new JsonResponse();
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
        jsonResponse.setSuccessed(result);
        return jsonResponse.toString();
    }

    /**
     * 注册或登录
     */
    @RequestMapping(value = "/login/{telephone}")
    @NeedNotLogin
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
        } else if (new Date().after(verifyCode.getCodeExpires())) {
            jsonResponse.setFailed("验证码已过期，请重新发送");
        }
        if (JsonResponse.RESULT_CODE_FAILED == jsonResponse.getResultCode()) {
            return jsonResponse;
        }

        /* 查询用户是否存在，如果存在则执行登录流程，否则执行注册流程 */
        MeetUser meetUser = meetUserMapper.selectByPhone(telephone);
        if (null != meetUser) {
            /* 用户已存在 */

        } else {
            /* TODO 用户不存在，注册 */

        }

        jsonResponse.setSuccessed("登录成功");
        return jsonResponse;
    }

    private String getToken() {
        return "";
    }

}
