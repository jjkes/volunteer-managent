package com.zj.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/6/26 17:48
 */
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Autowired
    private UserAuthInterceptor userAuthInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthInterceptor).addPathPatterns("/**");
    }
}
