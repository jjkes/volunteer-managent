package com.zj.task.service;

import com.zj.common.config.Result;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/6/25 10:34
 */

public interface RabbitMQService {
    /**
     * 测试消息
     * @author 赵健
     * @date 2023/6/25 13:42
     */
    Result<String> sendMessage();
}
