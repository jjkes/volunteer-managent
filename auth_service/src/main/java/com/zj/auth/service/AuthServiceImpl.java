package com.zj.auth.service;

import com.zj.auth.utls.RedisUtil;
import com.zj.common.config.Result;
import com.zj.common.constant.CommonEnum;
import com.zj.common.utils.JwtTokenUtil;
import com.zj.sys.entity.LoginUser;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/5 11:01
 */
@Service("authService")

public class AuthServiceImpl implements AuthService {

    @Value("${verify.enabled}")
    private boolean isVerify;
    @Override
    public ResponseEntity<Result> getAuthToken(LoginUser loginUser) {
        if(isVerify){

        }
        Result result = new Result();
        Subject subject = SecurityUtils.getSubject();
        String token = null;
        UsernamePasswordToken user = new UsernamePasswordToken(loginUser.getAccount(), loginUser.getPassword());
        try {
            subject.login(user);
            // 生成token
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        MultiValueMap<String,String> headers = new HttpHeaders();
        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }
}
