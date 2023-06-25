package com.zj.task.service.impl;

import com.rabbitmq.client.Channel;

import com.zj.common.config.Result;
import com.zj.common.constant.RabbitMQQueueConstant;
import com.zj.common.constant.StateEnum;
import com.zj.entities.task.entity.ScheduleTaskEntity;
import com.zj.task.config.RabbitMQConfig;
import com.zj.task.service.TaskService;
import org.springframework.amqp.core.Message;
import com.zj.task.service.RabbitMQService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.UUID;

import static com.zj.task.config.RabbitMQConfig.ROUTINGKEY;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/6/25 10:34
 */
@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    private final TaskService taskService;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQServiceImpl(TaskService taskService, RabbitTemplate rabbitTemplate) {
        this.taskService = taskService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = {RabbitMQQueueConstant.TASK_QUEUE})
    public void createTask(Object msg, Message message, Channel channel){
        byte[] body = message.getBody();
        try (InputStream ips = new ByteArrayInputStream(body);
             ObjectInputStream ois = new ObjectInputStream(ips);){
            ScheduleTaskEntity scheduleTask = (ScheduleTaskEntity)ois.readObject();
            taskService.RabbitMQTask(scheduleTask);
            System.err.println(scheduleTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试消息
     *
     * @author 赵健
     * @date 2023/6/25 13:42
     */
    @Override
    public Result<String> sendMessage() {
        ScheduleTaskEntity scheduleTask = new ScheduleTaskEntity();
        scheduleTask.setId(UUID.randomUUID().toString());
        scheduleTask.setExecuteTime("* * * * * *");
        scheduleTask.setTaskId(1);
        scheduleTask.setType(1);
        scheduleTask.setParam("执行定时任务");
        scheduleTask.setName("测试MQ");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, ROUTINGKEY, scheduleTask);
        return new Result<String>().setData("成功").setResultEnum(StateEnum.SUCCESS);
    }
}
