package com.zj.task.controller;

import com.zj.entities.task.entity.ScheduleTaskEntity;
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

    @GetMapping(value = "getTaskList")
    public List<ScheduleTaskEntity> getTaskList(ScheduleTaskEntity scheduleTask){
        return null;
    }

}
