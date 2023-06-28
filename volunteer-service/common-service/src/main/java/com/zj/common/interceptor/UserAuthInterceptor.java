package com.zj.common.interceptor;

import com.zj.common.annotations.Authentication;
import com.zj.common.constant.CommonEnum;
import com.zj.common.enums.AuthEnum;
import com.zj.common.exception.MyAuthException;
import com.zj.common.utils.RedisUtil;


import com.zj.entities.sys.dto.TokenUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/12 10:08
 */
@Component
@RequiredArgsConstructor
public class UserAuthInterceptor implements HandlerInterceptor {

    private String userId;

    private final RedisUtil redisUtil;

    private final HttpServletRequest httpServletRequest;

    public TokenUser getUserInfo() throws MyAuthException {
        String sessionRedisId = httpServletRequest.getHeader(CommonEnum.REDIS_SESSION_ID.getStr());
        if (sessionRedisId == null) {
            throw new MyAuthException("未登录，无法访问该资源！");
        }
        Object object = redisUtil.getObject(sessionRedisId);
        if (object == null) {
            throw new MyAuthException("登录过期，请重新登录！");
        }
        if(object instanceof TokenUser){
            TokenUser tokenUser = (TokenUser) object;
            return tokenUser;
        }
        return null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Annotation annotation = ((HandlerMethod) handler).getMethod().getAnnotation(Authentication.class);
        int sign = 0;
        AuthEnum[] auth;
        String authHandler;
        if (annotation != null) {
            Authentication authentication = (Authentication) annotation;
            if (authentication.type()==0) {
                sign=1;
            }else if(authentication.type()==1){
                auth = authentication.auth();
                sign=1;
            } else if (authentication.type() == 2) {
                auth = authentication.auth();
                authHandler = authentication.methodHandler();
                sign=1;
            }
        }
        // tokenUser对象，存储在redis中的登录用户信息，此处只取出该值
        TokenUser tokenUser;
        String sessionRedisId = httpServletRequest.getHeader(CommonEnum.REDIS_SESSION_ID.getStr());
        if (StringUtils.isNotBlank(sessionRedisId)) {
            Object object = redisUtil.getObject(sessionRedisId);
            if(object != null){
                if(object instanceof TokenUser){
                    tokenUser = (TokenUser) object;
                    request.setAttribute("tokenUser",tokenUser);
                }
            }
        }
        // 鉴权
//        tokenUser.getRoleId(); auth
        if (sign==1) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
