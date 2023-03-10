package com.zj.auth.controller;

import com.zj.auth.service.AuthService;
import com.zj.common.config.Result;
import com.zj.sys.dto.TokenUser;
import com.zj.sys.entity.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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


    @PostMapping(value = "sys/login")
    public Result<Map<String, Object>> sysLogin(@RequestBody LoginUser loginUser){
        ResponseEntity<Result> authToken = authService.getAuthToken(loginUser);
        return null;
    }
}
