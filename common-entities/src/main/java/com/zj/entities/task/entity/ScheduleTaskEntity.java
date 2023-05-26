package com.zj.entities.task.entity;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/25 17:30
 */

public class ScheduleTaskEntity {
    private String id;
    private String name;
    private Integer type;
    private String executeTime;
    private Integer taskId;

    private String param;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
