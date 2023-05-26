package com.zj.task.taskHandler;

/**
 * @author 赵健
 * @version 1.0
 * @description: 实现该类方法之后需要在数据库中配置基表和在ScheduleTaskEnum中配置枚举
 * @date 2023/5/26 13:37
 */

public interface Task {
    void execute(String param);
}
