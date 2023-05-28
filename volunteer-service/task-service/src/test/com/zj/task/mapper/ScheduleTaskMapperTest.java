package com.zj.task.mapper;

import com.zj.task.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleTaskMapperTest extends BaseTest {

    @Autowired
    private ScheduleTaskMapper mapper;

    @Test
    public void testGetAllTask() {
        int taskCount = mapper.getTaskCount(null);
        System.err.println(taskCount);
    }

    @Test
    public void testGetTaskById() {
    }

    @Test
    public void testGetTaskCount() {
    }

    @Test
    public void testGetTaskListForLimit() {
    }
}