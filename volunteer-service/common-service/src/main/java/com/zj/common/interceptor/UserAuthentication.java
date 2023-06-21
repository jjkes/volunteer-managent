package com.zj.common.interceptor;

import com.zj.common.constant.CommonEnum;
import com.zj.common.exception.MyAuthException;
import com.zj.common.utils.RedisUtil;


import com.zj.entities.sys.dto.TokenUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/12 10:08
 */
@Component
@RequiredArgsConstructor
public class UserAuthentication {

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
}
