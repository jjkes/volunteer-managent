package com.zj.task.controller;

import com.zj.common.config.Result;
import com.zj.entities.task.entity.ScheduleTaskEntity;
import com.zj.task.service.RabbitMQService;
import com.zj.task.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/28 14:52
 */
@RestController
@RequestMapping("task")
public class TaskController {
    private final RabbitMQService rabbitMQService;
    private final ScheduleService scheduleService;

    public TaskController(RabbitMQService rabbitMQService, ScheduleService scheduleService) {
        this.rabbitMQService = rabbitMQService;
        this.scheduleService = scheduleService;
    }

    @GetMapping(value = "getTaskList")
    public Result<?> getTaskList(ScheduleTaskEntity scheduleTask){
        return scheduleService.getTaskList(scheduleTask, 1, 10);
    }

    @GetMapping(value = "startATask")
    public Result<String> startATask(){
        return rabbitMQService.sendMessage();
    }

}
