package com.example.meet.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * 返回给客户端的Response
 *
 * @author baiyuliang
 * @version 1.0
 * @since 1.0
 */
@Data
public class JsonResponse {
    /**
     * 通用结果：成功
     */
    public static final int RESULT_CODE_SUCCESS = 0;

    /**
     * 通用结果：失败
     */
    public static final int RESULT_CODE_FAILED = 233;

    /**
     * 接口执行结果
     */
    private int resultCode;

    /**
     * 错误提示信息
     */
    private String errorMsg;

    /**
     * 返回的数据
     */
    private Object data;

    public JsonResponse() {
    }

    /**
     * 设置失败的返回结果
     *
     * @param errorMessage 错误信息
     */
    public void setFailed(String errorMessage) {
        this.resultCode = RESULT_CODE_FAILED;
        this.errorMsg = errorMessage;
    }

    /**
     * 设置成功的返回结果
     *
     * @param object 返回的data字段的数据
     */
    public void setSuccessed(Object object) {
        this.resultCode = RESULT_CODE_SUCCESS;
        this.data = object;
    }

    @Override
    public String toString() {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        return JSONObject.toJSONString(this, SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect);
    }

}