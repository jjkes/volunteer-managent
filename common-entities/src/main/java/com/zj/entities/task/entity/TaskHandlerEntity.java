package com.zj.entities.task.entity;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/26 14:21
 */

public class TaskHandlerEntity {
    Integer id;
    String name;
    String paramFormat;

    String handler;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParamFormat() {
        return paramFormat;
    }

    public void setParamFormat(String paramFormat) {
        this.paramFormat = paramFormat;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }
}
