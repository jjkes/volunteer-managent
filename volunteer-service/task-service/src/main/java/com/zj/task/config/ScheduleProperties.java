package com.zj.task.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/25 17:01
 */
@ConfigurationProperties(prefix = "schedule")
@Configuration
public class ScheduleProperties {
    private Integer poolSize=1;

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }
}
