package com.zj.entities.task.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/25 17:30
 */
@Alias("ScheduleTaskEntity")
@Data
public class ScheduleTaskEntity {
    private String id;
    private String name;
    private Integer type;
    private String executeTime;
    private Integer taskId;

    private String param;

}
