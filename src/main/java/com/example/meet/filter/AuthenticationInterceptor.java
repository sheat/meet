package com.example.meet.filter;

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
            String accessToken = request.getParameter(ACCESS_TOKEN);
            if (null == accessToken) {
                throw new RuntimeException("无token，请重新登录");
            }
            Claims claims = TokenUtils.parseJWT(accessToken);
            String telephone = claims.getId();
            MeetUser user = meetUserMapper.selectByPhone(telephone);
            if (user == null) {
                throw new RuntimeException("用户不存在，请注册");
            }
            // 当前登录用户
            request.setAttribute(CurrentUserConstants.CURRENT_USER, user);
            return true;
        }
        return true;
    }


}
