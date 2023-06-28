package com.zj.task.service;

import com.zj.common.config.Result;
import com.zj.entities.task.entity.ScheduleTaskEntity;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/25 17:14
 */

public interface TaskService {
    /**
     * rabbitMQ 接受消息并开启定时任务
     * @author 赵健
     * @date 2023/6/25 13:34
     */
    void RabbitMQTask(ScheduleTaskEntity scheduleTask);

    /**
     * 暂停一个定时任务
     * @author 赵健
     * @date 2023/6/27 15:22
     */
    Result<?> pauseTask(String id);
}
