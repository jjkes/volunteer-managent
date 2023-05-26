package com.zj.entities.task.constant;

/**
 * @author 赵健
 * @version 1.0
 * @description: 定时任务处理器beanName的枚举类
 * @date 2023/5/26 14:14
 */

public enum ScheduleTaskEnum {
    /**
    * 发送通知：轮询通知，向所有用户发送通知
    */
    SEND_NOTICE("发送通知","sendNotice","");


    final String name;

    final String handler;

    final String paramForm;

    ScheduleTaskEnum(String name, String handler, String paramForm) {
        this.name = name;
        this.handler = handler;
        this.paramForm = paramForm;
    }
}
