package com.zj.sys.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/1/11 10:25
 */
@Component
public class RedisUtils {

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    private static final Long EXPIRE_TIME = 10L;

    private final StringRedisTemplate stringRedisTemplate;

    public RedisUtils(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private Boolean getLock(String key){
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", EXPIRE_TIME, TimeUnit.SECONDS);
        return aBoolean;
    }

    private void unlock(String key){
        Boolean delete = stringRedisTemplate.delete(key);
    }

    public void set(String key, String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }
    public void setStringValues(String key, Object value, Long time, TimeUnit timeUnit){
//        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value),time,timeUnit);
//        stringRedisTemplate.opsForValue().setIfAbsent();
    }

    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }




}
