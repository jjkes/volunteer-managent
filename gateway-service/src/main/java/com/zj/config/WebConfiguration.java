package com.zj.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/6/27 16:55
 */
@Configuration
public class WebConfiguration {

    /**
     * 修改负载均衡策略，使其选择每次并发处理量最少的那台机器
     * @author 赵健
     * @date 2023/6/27 16:56
     */

    @Bean
    public IRule myRule(){
        return new BestAvailableRule();
    }
}
