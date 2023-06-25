package com.zj.task.test.rabbitMq;

import com.zj.entities.task.entity.ScheduleTaskEntity;
import com.zj.task.config.RabbitMQConfig;
import com.zj.task.test.BaseTest;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/6/25 10:14
 */

public class RabbitMqSenderTest extends BaseTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void sendMessage(){
        ScheduleTaskEntity scheduleTask = new ScheduleTaskEntity();
        scheduleTask.setId("dfd");
        scheduleTask.setTaskId(1);
        scheduleTask.setType(1);
        scheduleTask.setParam("fdsfsdf");
        scheduleTask.setExecuteTime("* * * * * *");
//        String message="我爱我家";
//        System.err.println(message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTINGKEY,scheduleTask);
//        Message build = MessageBuilder.withBody("wer".getBytes(StandardCharsets.UTF_8)).build();
//        rabbitTemplate.send(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTINGKEY,build);
//        Message build1 = MessageBuilder.withBody("asdfsad".getBytes(StandardCharsets.UTF_8)).build();
//        rabbitTemplate.send(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTINGKEY,build1);
//        Message build2 = MessageBuilder.withBody("ggrewer".getBytes(StandardCharsets.UTF_8)).build();
//        rabbitTemplate.send(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTINGKEY,build2);
//        Message build3 = MessageBuilder.withBody("werbrgr".getBytes(StandardCharsets.UTF_8)).build();
//        rabbitTemplate.send(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTINGKEY,build3);
//        rabbitTemplate.
        System.err.println("发送完成");
    }
}
