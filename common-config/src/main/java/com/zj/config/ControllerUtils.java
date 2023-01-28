package com.zj.config;

import com.zj.sys.dto.TokenUser;
import com.zj.utils.JwtTokenUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 22:51
 */

public class ControllerUtils {
    public static TokenUser solveToken(HttpServletRequest request){
        String authorizationToken = request.getHeader("AuthorizationToken");
        if(StringUtils.isEmpty(authorizationToken)){
            throw new NullPointerException("token为空");
        }
        try {
            TokenUser tokenUserFromToken = JwtTokenUtil.getTokenUserFromToken(authorizationToken);
            return tokenUserFromToken;
        }catch (Exception e){
            throw e;
        }
    }
}
