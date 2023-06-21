package com.zj.entities.task.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/26 14:21
 */
@Alias("TaskHandlerEntity")
@Data
public class TaskHandlerEntity {
    Integer id;
    String name;
    String paramFormat;

    String handler;
}
