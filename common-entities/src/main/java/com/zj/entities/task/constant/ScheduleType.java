package com.zj.entities.task.constant;

/**
 * @author 赵健
 * @version 1.0
 * @description: 定时任务的类型
 * @date 2023/5/26 10:28
 */

public enum ScheduleType {
    CRON(1),
    INTERVAL(2),
    TIME(3);

    final int type;
    ScheduleType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
