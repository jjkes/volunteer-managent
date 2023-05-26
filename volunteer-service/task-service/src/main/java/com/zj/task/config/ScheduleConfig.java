package com.zj.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/25 17:00
 */
@Configuration
public class ScheduleConfig {
    private final ScheduleProperties scheduleProperties;

    public ScheduleConfig(ScheduleProperties scheduleProperties) {
        this.scheduleProperties = scheduleProperties;
    }

    @Bean("task")
    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(scheduleProperties.getPoolSize());
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setAwaitTerminationMillis(60);
        threadPoolTaskScheduler.setThreadNamePrefix("task-");
        return threadPoolTaskScheduler;
    }
}
