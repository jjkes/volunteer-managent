package com.zj.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.zj.common", "com.zj.file"})
@EnableEurekaClient
public class XmlApplication {
    public static void main(String[] args) {
        SpringApplication.run(XmlApplication.class, args);
    }
}