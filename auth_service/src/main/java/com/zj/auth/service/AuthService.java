package com.zj.auth.service;

import com.zj.common.config.Result;
import com.zj.sys.entity.LoginUser;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/5 11:00
 */

public interface AuthService {
    ResponseEntity<Result> getAuthToken(LoginUser loginUser);
}