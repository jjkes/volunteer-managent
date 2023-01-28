package com.zj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2022/11/7 21:31
 */
@Component
public class RedisDao {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

}
