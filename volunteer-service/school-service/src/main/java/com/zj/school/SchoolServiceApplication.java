package com.zj.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
* @discription application启动类
* @author 赵健
* @date 2023/2/1 21:24
* @version 1.0
*/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SchoolServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolServiceApplication.class, args);
    }
}