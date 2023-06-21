package com.zj.auth.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/4 21:50
 */
@Configuration
public class ShiroConfiguration {
    private final long SESSION_TIMEOUT = 1000 * 60 * 30;

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("myWebSecurityManager")DefaultWebSecurityManager myWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(myWebSecurityManager);
        Map<String, Filter> filterMap = new HashMap<>();
        shiroFilterFactoryBean.setFilters(filterMap);
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(objectObjectHashMap);
        return shiroFilterFactoryBean;
    }


    @Bean("redisSessionManager")
    public DefaultSessionManager setSessionManager(CacheManager cacheManager) {
        DefaultSessionManager sessionManager = new DefaultSessionManager();
        sessionManager.setGlobalSessionTimeout(SESSION_TIMEOUT);
        sessionManager.setCacheManager(cacheManager);
        return sessionManager;
    }

    @Bean("myWebSecurityManager")
    public DefaultWebSecurityManager securityManager(
            @Qualifier("redisSessionManager") DefaultSessionManager sessionManager,
            @Qualifier("cacheManager") CacheManager cacheManager,
            @Qualifier("usernamePasswordRealm") UsernamePasswordRealm usernamePasswordRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(usernamePasswordRealm);
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }


    @Bean("cacheManager")
    public CacheManager setCacheManager(RedisCache redisCache){
        CacheManager cacheManager = new CacheManager() {
            @Override
            public <K, V> Cache<K, V> getCache(String s) throws CacheException {
                redisCache.setRedisCacheName(s);
                return redisCache;
            }
        };
        return cacheManager;
    }


}
