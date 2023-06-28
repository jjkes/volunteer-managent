package com.zj.task.controller;

import com.zj.common.annotations.Authentication;
import com.zj.common.config.Result;
import com.zj.common.enums.AuthEnum;
import com.zj.common.interceptor.UserAuthInterceptor;
import com.zj.common.utils.RedisUtil;
import com.zj.entities.sys.dto.TokenUser;
import com.zj.entities.task.entity.ScheduleTaskEntity;
import com.zj.task.service.RabbitMQService;
import com.zj.task.service.ScheduleService;
import com.zj.task.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    private final TaskService taskService;

    private final UserAuthInterceptor userAuthInterceptor;

    public TaskController(RabbitMQService rabbitMQService, ScheduleService scheduleService, RedisUtil redisUtil, TaskService taskService, UserAuthInterceptor userAuthInterceptor) {
        this.rabbitMQService = rabbitMQService;
        this.scheduleService = scheduleService;
        this.taskService = taskService;
        this.userAuthInterceptor = userAuthInterceptor;
    }

    @GetMapping(value = "getTaskList")
    @Authentication(type = 1,description = "获取所有定时任务的列表",
            auth = {AuthEnum.定时任务查看权限})
    public Result<?> getTaskList(ScheduleTaskEntity scheduleTask, @RequestAttribute("tokenUser") TokenUser tokenUser){
        System.err.println(tokenUser);
        System.err.println(userAuthInterceptor.getUserInfo());
        return scheduleService.getTaskList(scheduleTask, 1, 10);
    }

    @GetMapping(value = "startATask")
    @Authentication(type = 1,description = "获取所有定时任务的列表",
            auth = {AuthEnum.定时任务管理权限})
    public Result<String> startATask(){
        return rabbitMQService.sendMessage();
    }

    @GetMapping(value = "stopTask")
    @Authentication(type = 1,description = "暂停一个定时任务",auth = {AuthEnum.定时任务管理权限})
    public Result<?> pauseTask(@RequestParam("id")String id){
        return taskService.pauseTask(id);
    }

}
