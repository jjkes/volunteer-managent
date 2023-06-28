package com.zj.auth.controller;

import com.zj.auth.service.AuthService;
import com.zj.common.annotations.Authentication;
import com.zj.common.config.Result;
import com.zj.common.constant.StateEnum;
import com.zj.common.exception.MyAuthException;
import com.zj.common.interceptor.UserAuthInterceptor;

import com.zj.common.utils.JwtTokenUtil;
import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.sys.entity.LoginUser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 赵健
 * @version 1.0
 * @description: 身份验证
 * @date 2023/3/4 21:47
 */
@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final UserAuthInterceptor userAuthInterceptor;


    @PostMapping(value = "sys/login")
    public ResponseEntity<Result> sysLogin(@RequestBody LoginUser loginUser) throws MyAuthException {
        ResponseEntity<Result> authToken = authService.getAuthToken(loginUser);
        return authToken;
    }

    @GetMapping(value = "sys/getTokenUser")
    public Result<TokenUser> getTokenUser() throws MyAuthException {
        TokenUser userInfo = userAuthInterceptor.getUserInfo();
        return new Result<TokenUser>().setResultEnum(StateEnum.SUCCESS).setData(userInfo);
    }

    @GetMapping(value = "getRouterKeyFromToken")
    @Authentication(type = 0,description = "解析token")
    public String getRouterKeyFromToken(String token){
        String tokenUserFromToken = null;
        try {
            tokenUserFromToken = JwtTokenUtil.getTokenUserFromToken(token);
        } catch (Exception e) {
            logger.error("解析token错误：《{}》",e.getMessage(),e);
        }
        return tokenUserFromToken;
    }

    @GetMapping(value = "refreshTokenByToken")
    @Authentication(type = 0,description = "刷新token")
    public String refreshTokenByToken(String token){
        String refreshToken = null;
        try {
            refreshToken = JwtTokenUtil.refreshToken(token);
        } catch (Exception e) {
            logger.error("刷新token错误：《{}》",e.getMessage(),e);
        }
        return refreshToken;
    }
}
