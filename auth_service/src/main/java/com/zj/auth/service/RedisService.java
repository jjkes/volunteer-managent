package com.zj.auth.service;

import com.zj.sys.dto.TokenUser;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/12 10:33
 */

public interface RedisService {
    String saveUserToRedis(TokenUser tokenUser);
}
