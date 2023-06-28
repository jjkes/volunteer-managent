package com.zj.auth.service.impl;

import com.zj.auth.service.AuthService;
import com.zj.auth.service.RedisService;
import com.zj.auth.webService.SysService;
import com.zj.common.config.Result;
import com.zj.common.constant.CommonEnum;
import com.zj.common.constant.StateEnum;
import com.zj.common.exception.MyAuthException;

import com.zj.common.utils.JwtTokenUtil;
import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.sys.entity.LoginUser;
import com.zj.entities.sys.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/5 11:01
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SysService sysService;

    private final RedisService redisService;
    @Value("${verify.enabled}")
    private boolean isVerify;

    @Override
    public ResponseEntity<Result> getAuthToken(LoginUser loginUser) throws MyAuthException {
        if(isVerify){
            // 图片验证码

        }
        Result result = new Result();
        if(loginUser.getAccount() != null && loginUser.getPassword() != null){
            User user = sysService.getUserByUsername(loginUser.getAccount());
            if(user == null){
                result.setResultEnum(StateEnum.LOGIN_FAILED);
            } else if (!user.getPassword().equals(loginUser.getPassword())) {
                result.setResultEnum(StateEnum.LOGIN_FAILED);
            } else{
                TokenUser tokenUser = new TokenUser();
                tokenUser.setId(user.getId());
                tokenUser.setUsername(user.getAccount());
                tokenUser.setUnitId(user.getSchoolId());
                tokenUser.setRoleId(user.getRoleId());
                String redisKey = redisService.saveUserToRedis(tokenUser);
                String token = JwtTokenUtil.generateTokenForStr(redisKey);
                result.setData(token);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.set(CommonEnum.TOKEN_STR.getStr(),token);
                return  new ResponseEntity<>(result,httpHeaders,HttpStatus.OK);
            }
        }else{
            result.setResultEnum(StateEnum.LOGIN_PARAMS);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
//    @Override
//    public ResponseEntity<Result> getAuthToken(LoginUser loginUser) {
//        if(isVerify){
//
//        }
//        Result result = new Result();
//        Subject subject = SecurityUtils.getSubject();
//        String token = null;
//        UsernamePasswordToken user = new UsernamePasswordToken(loginUser.getAccount(), loginUser.getPassword());
//        try {
//            subject.login(user);
//            // 生成token
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//        }
//        MultiValueMap<String,String> headers = new HttpHeaders();
//        return new ResponseEntity<Result>(result, HttpStatus.OK);
//    }
}
