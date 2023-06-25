package com.zj.task.service.impl;

import com.zj.common.config.Result;
import com.zj.entities.task.entity.ScheduleTaskEntity;
import com.zj.task.mapper.ScheduleTaskMapper;
import com.zj.task.service.ScheduleService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/28 16:41
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleTaskMapper scheduleTaskMapper;

    public ScheduleServiceImpl(ScheduleTaskMapper scheduleTaskMapper) {
        this.scheduleTaskMapper = scheduleTaskMapper;
    }

    /**
     * 分页查询ScheduleTask列表
     *
     * @param scheduleTask 定时任务实体
     * @param page 分页
     * @param pageSize 分页大小
     * @author 赵健
     * @date 2023/5/28 16:44
     */
    @Override
    public Result<?> getTaskList(ScheduleTaskEntity scheduleTask, int page, int pageSize) {
        int taskCount = scheduleTaskMapper.getTaskCount(scheduleTask);
        List<ScheduleTaskEntity> taskListForLimit = scheduleTaskMapper.getTaskListForLimit(scheduleTask.getId(),scheduleTask.getName(),scheduleTask.getTaskId(),scheduleTask.getType(), page-1, pageSize);
        Result<List<ScheduleTaskEntity>> result = new Result<>();
        result.setCount(taskCount);
        result.setData(taskListForLimit);
        return result;
    }
}
