package com.zj.task.config;

import com.zj.common.constant.RabbitMQQueueConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/6/25 10:08
 */
@Component
public class RabbitMQConfig {

    public static final String EXCHANGE ="volunteer.task";
    public static final String ROUTINGKEY="volunteer.#.task.#";
    @Bean(EXCHANGE)
    public Exchange InitExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build();
    }

    /**
     * 初始化队列
     * @author 赵健
     * @date 2023/6/25 11:00
     */
    @Bean(RabbitMQQueueConstant.TASK_QUEUE)
    public Queue InitQueue(){
        return new Queue(RabbitMQQueueConstant.TASK_QUEUE);
    }

    /**
     * 把exchange和queue按照路由规则绑定起来.
     * @author 赵健
     * @date 2023/6/25 11:01
     */
    @Bean
    public Binding InitBinding(@Qualifier(RabbitMQQueueConstant.TASK_QUEUE) Queue queue,
                                              @Qualifier(EXCHANGE) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY).noargs();
    }
}
