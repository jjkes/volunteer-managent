package com.zj.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/25 9:33
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties
@EnableFeignClients
public class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class,args);
    }
}
