package com.zj.task.mapper;

import com.zj.task.entity.ScheduleTaskEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/25 17:24
 */
@Mapper
public interface ScheduleTaskMapper {

    List<ScheduleTaskEntity> getAllTask();
}