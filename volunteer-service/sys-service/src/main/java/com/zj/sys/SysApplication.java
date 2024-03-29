package com.zj.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author a1204
 * @version 1.0
 * @description: TODO
 * @date 2022/9/25 16:14
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zj"})
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
public class SysApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class, args);
    }
}
