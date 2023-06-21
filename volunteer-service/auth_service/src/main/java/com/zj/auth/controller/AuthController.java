package com.zj.auth.controller;

import com.zj.auth.service.AuthService;
import com.zj.common.config.Result;
import com.zj.common.constant.StateEnum;
import com.zj.common.exception.MyAuthException;
import com.zj.common.interceptor.UserAuthentication;

import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.sys.entity.LoginUser;
import lombok.RequiredArgsConstructor;
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

    private final AuthService authService;
    private final UserAuthentication userAuthentication;


    @PostMapping(value = "sys/login")
    public ResponseEntity<Result> sysLogin(@RequestBody LoginUser loginUser) throws MyAuthException {
        ResponseEntity<Result> authToken = authService.getAuthToken(loginUser);
        return authToken;
    }

    @GetMapping(value = "sys/getTokenUser")
    public Result<TokenUser> getTokenUser() throws MyAuthException {
        TokenUser userInfo = userAuthentication.getUserInfo();
        return new Result<TokenUser>().setResultEnum(StateEnum.SUCCESS).setData(userInfo);
    }
}
