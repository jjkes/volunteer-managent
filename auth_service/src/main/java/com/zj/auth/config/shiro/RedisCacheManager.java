package com.zj.auth.config.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/5 14:45
 */
@Component
public class RedisCacheManager extends AbstractCacheManager {
    @Autowired
    private RedisCache redisCache;
    @Override
    protected Cache createCache(String s) throws CacheException {
        redisCache.setRedisCacheName(s);
        return redisCache;
    }
}
