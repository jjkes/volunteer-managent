package com.zj.auth.config.shiro;

import com.zj.auth.utls.RedisUtil;
import com.zj.common.utils.HashUtil;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/5 9:35
 */
@Component
public class RedisCache<K, V> implements Cache<K,V>{


    @Autowired
    private RedisUtil redisUtil;


    private String redisCacheName;

    public void setRedisCacheName(String redisCacheName) {
        this.redisCacheName = redisCacheName;
    }

    @Override
    public V get(K k) throws CacheException {
        return (V)redisUtil.getObject(String.valueOf(HashUtil.getStringHashCode(k.toString()+redisCacheName)));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return (V)redisUtil.setObject(String.valueOf(HashUtil.getStringHashCode(k.toString()+redisCacheName)),v);
    }

    @Override
    public V remove(K k) throws CacheException {
        return (V)redisUtil.removeKeys(String.valueOf(HashUtil.getStringHashCode(k.toString()+redisCacheName)));
    }

    @Override
    public void clear() throws CacheException {
        redisUtil.clearKeys();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
