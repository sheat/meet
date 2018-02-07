package com.example.meet.filter;

import com.example.meet.common.JsonResponse;
import com.example.meet.dao.MeetUserMapper;
import com.example.meet.model.MeetUser;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Alex
 * Date: 2018-02-06
 * Time: 14:26
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    public final static String ACCESS_TOKEN = "accessToken";

    @Autowired
    private MeetUserMapper meetUserMapper;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JsonResponse jsonResponse = new JsonResponse();
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        NeedNotLogin methodAnnotation = method.getAnnotation(NeedNotLogin.class);
        // 有 @NeedNotLogin 注解，不需要登录验证
        if (null == methodAnnotation) {
            // 判断是否存在令牌信息，如果存在，则允许登录
            String accessToken = request.getHeader(ACCESS_TOKEN);
            if (null == accessToken) {
                jsonResponse.setFailed("没有发送token");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write(jsonResponse.toString());
                return false;
            }

            Claims claims;
            try {
                claims = TokenUtils.parseJWT(accessToken);
            } catch (Exception e) {
                e.printStackTrace();
                jsonResponse.setFailed("token解析失败");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write(jsonResponse.toString());
                return false;
            }

            String telephone = claims.getId();
            String storedToken = meetUserMapper.selectTokenByPhone(telephone);
            if (storedToken == null || !accessToken.equals(storedToken)) {
                jsonResponse.setFailed("token验证失败");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write(jsonResponse.toString());
                return false;
            }

            // 当前登录用户
            request.setAttribute(CurrentUserConstants.CURRENT_USER, telephone);
            return true;
        }
        return true;
    }


}
