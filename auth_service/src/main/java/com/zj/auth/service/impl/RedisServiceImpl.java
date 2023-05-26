package com.zj.auth.service.impl;

import com.zj.auth.service.RedisService;

import com.zj.common.utils.RedisUtil;
import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.sys.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/12 10:33
 */
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    private final RedisUtil redisUtil;

    public String saveUserToRedis(TokenUser tokenUser){
        String uuid = UUID.randomUUID().toString();
        redisUtil.setObject(uuid, tokenUser);
        return uuid;
    }
}
