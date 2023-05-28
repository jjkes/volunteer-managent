package com.zj.task.service;

import com.zj.common.config.Result;
import com.zj.entities.task.entity.ScheduleTaskEntity;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/28 16:41
 */

public interface ScheduleService {
    /**
     * 分页查询ScheduleTask列表
     * @author 赵健
     * @date 2023/5/28 16:44
     */
    public Result<?> getTaskList(ScheduleTaskEntity scheduleTask,int page,int pageSize);

}
