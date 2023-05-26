package com.zj.task.taskHandler.handler;

import com.zj.task.taskHandler.Task;
import org.springframework.stereotype.Service;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/26 13:40
 */
@Service("sendNotice")
public class SendNoticeTask implements Task {
    @Override
    public void execute(String param) {
        System.err.println("发送通知："+param);
    }
}
