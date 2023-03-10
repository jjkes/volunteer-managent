package com.zj.auth.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/2/5 13:01
 */
@Component
public class JedisPoolFactory {

    private final RedisConfig redisConfig;
    @Autowired
    public JedisPoolFactory(RedisConfig redisConfig){
        this.redisConfig = redisConfig;
    }

    @Bean(name = "jedisPool")
    public JedisPool JedisPoolFactory(){
        try{
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
            jedisPoolConfig.setMinIdle(redisConfig.getMinIdle());
            jedisPoolConfig.setMaxTotal(redisConfig.getMaxActive());
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPoolConfig.setTestOnReturn(true);
            JedisPool jedisPool = new JedisPool(jedisPoolConfig,redisConfig.getHost(),redisConfig.getPort(),
                    redisConfig.getTimeout(),redisConfig.getPassword(),redisConfig.getDatabase());
            return jedisPool;
        }catch(IllegalStateException e){
            e.printStackTrace();
        }
        return null;
    }

}
