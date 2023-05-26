package com.zj.task.mapper;

import com.zj.entities.task.entity.TaskHandlerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/26 14:47
 */
@Mapper
public interface TaskHandlerMapper {
    TaskHandlerEntity getTaskHandlerById(Integer id);
}
