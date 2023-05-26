package com.zj.task.service.impl;

import com.zj.common.exception.MyCommonException;
import com.zj.common.utils.DateUtil;
import com.zj.common.verify.VerifyStr;
import com.zj.entities.task.constant.ScheduleType;
import com.zj.entities.task.entity.ScheduleTaskEntity;
import com.zj.entities.task.entity.TaskHandlerEntity;
import com.zj.task.mapper.ScheduleTaskMapper;
import com.zj.task.mapper.TaskHandlerMapper;
import com.zj.task.service.ScheduleService;
import com.zj.task.taskHandler.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;


/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/5/25 17:14
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    @Autowired
    private TaskHandlerMapper taskHandlerMapper;
    @Autowired
    private ScheduleTaskMapper scheduleTaskMapper;
    @Autowired
    private ApplicationContext applicationContext;

    /**
    * 定时任务执行的线程池
    */
    @Autowired
    private ThreadPoolTaskScheduler task;
    /**
    * 定时任务执行中缓存列表
    */
    private final Map<String, ScheduledFuture<?>> scheduledFutureMap = new ConcurrentHashMap<>();
    /**
    * 定时任务暂停的缓存列表
    */
    private final Map<String, ScheduledFuture<?>> stopedScheduledFutureMap = new ConcurrentHashMap<>();

    /**
     * 当程序启动时从数据库中读取之前开启的定时任务，完成定时任务的初始话
     * @author 赵健
     * @date 2023/5/25 17:21
     */
    @Bean
    private void initScheduleTask(){
        int taskSuccessCount = 0;
        int taskFailCount=0;
      List<ScheduleTaskEntity> taskEntityList   = scheduleTaskMapper.getAllTask();
      logger.info("初始化定时任务开始！");
        for (ScheduleTaskEntity task : taskEntityList) {
            boolean isStart = addTask(task);
            if (!isStart) {
                taskSuccessCount++;
                logger.info("定时任务《{}》启动成功！任务id：{}",task.getName(),task.getId());
            }else{
                taskFailCount++;
                logger.error("定时任务《{}》启动失败！任务id：{}，参数：{}",task.getName(),task.getId(),task.getParam());
            }
        }
        logger.info("初始化定时任务结束！启动成功个数：{}个，启动失败个数：{}个",taskSuccessCount,taskFailCount);

    }

    private String generateTaskName(String scheduleTaskId){
        return "定时任务:"+"-"+scheduleTaskId;
    }


    private boolean addTask(ScheduleTaskEntity scheduleTask){
        // 查询定时任务的handler
        String key =generateTaskName(scheduleTask.getId());
        ScheduledFuture<?> schedule = null;

        // 先从任务列
        if (stopedScheduledFutureMap.get(key)!=null || scheduledFutureMap.get(key) != null) {
            if (scheduledFutureMap.get(key) != null) {
                throw new MyCommonException("定时任务已经启动过啦！");
            }
            schedule = stopedScheduledFutureMap.get(key);
        }else{
            TaskHandlerEntity taskHandlerEntity = taskHandlerMapper.getTaskHandlerById(scheduleTask.getTaskId());
            // 通过spring上下文路径获取指定beanName的bean
            Task t = (Task)applicationContext.getBean(taskHandlerEntity.getHandler());
            // 获取任务中json格式的参数，传入task的execute方法中，进行处理
            String param = scheduleTask.getParam();
            schedule = this.task.schedule(() -> {
                t.execute(param);
            }, getTrigger(scheduleTask));
        }
        if (schedule != null) {
            scheduledFutureMap.put(key, schedule);
            return true;
        }else{
            return false;
        }
    }
    /**
     * 暂停任务，任务数据缓存在内存中，可以直接开启
     * @author 赵健
     * @date 2023/5/26 17:10
     */
    private boolean pauseTask(ScheduleTaskEntity scheduleTask){
        String key = generateTaskName(scheduleTask.getId());
        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(key);
        if (scheduledFuture.isDone()) {
            boolean isCancel = scheduledFuture.cancel(false);
            if (isCancel) {
                scheduledFutureMap.remove(key);
            }
        }else{
            while (!scheduledFuture.isDone()){
                System.err.println("尝试关闭任务");
                boolean isCancel = scheduledFuture.cancel(false);
                if (isCancel) {
                    stopedScheduledFutureMap.put(key,scheduledFutureMap.remove(key));
                }
            }
        }
        return true;
    }
    /**
     * 终止任务，不缓存到内存，再次开启需要读取数据库中信息
     * @author 赵健
     * @date 2023/5/26 17:10
     */
    private boolean terminateTask(ScheduleTaskEntity scheduleTask){
        boolean isCancel = false;
        String key = generateTaskName(scheduleTask.getId());
        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(key);
        if (scheduledFuture.isDone()) {
            isCancel = scheduledFuture.cancel(false);
            if (isCancel) {
                scheduledFutureMap.remove(key);
            }
        }else{
            int failCount=0;
            while (!scheduledFuture.isDone()){
                System.err.println("尝试关闭任务");
                isCancel = scheduledFuture.cancel(false);
                failCount++;
                // 每过1秒钟重试一次，尝试3秒后如果还是没有关闭，则关闭失败
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (failCount>3) {
                    break;
                }
            }
        }
        return isCancel;
    }

    /**
     * 获取一个触发器，这个触发器用来控制程序执行完成之后下一次执行的时间
     * @author 赵健
     * @date 2023/5/26 10:45
     */
    private Trigger getTrigger(ScheduleTaskEntity scheduleTask){
        Trigger trigger = null;
        Integer type = scheduleTask.getType();
        String executeTime = scheduleTask.getExecuteTime().trim();
        if (type == ScheduleType.CRON.getType()) {
            // cron表达式
            VerifyStr.verCronStr(executeTime);
            trigger = new CronTrigger(executeTime,TimeZone.getDefault());
        } else if (type == ScheduleType.INTERVAL.getType()) {
            // 间隔时间,第一次创建此定时任务后，程序会在10分钟之后执行第一次，之后再按照间隔执行
            try {
                trigger = (TriggerContext triggerContext) -> {
                    Date date = triggerContext.lastActualExecutionTime();
                    if (date == null) {
                        date = new Date();
                        date.setTime(new Date().getTime() + (1000 * 60 * 10));
                        return date;
                    }
                    long time = date.getTime();
                    long l = Long.parseLong(executeTime);
                    Date nextDate = new Date();
                    nextDate.setTime(time + l);
                    return nextDate;
                };
            }catch (Exception e){
                logger.error("定时任务创建失败，任务id：{}，任务参数：{}，错误信息：\n",scheduleTask.getId(),scheduleTask.getParam(),e);
                throw new MyCommonException("定时任务创建失败，请检查您输入的日期间隔是否正确！");
            }
        } else if (type == ScheduleType.TIME.getType()) {
            try {
                trigger = (TriggerContext triggerContext) -> {
                    Date now = new Date();
                    Date executeDate = DateUtil.parseStrToDate(executeTime, DateUtil.DateFormat.yyyyMMddTHHmmss);
                    boolean after = executeDate.after(now);
                    if (after) {
                        return executeDate;
                    } else {
                        return null;
                    }
                };
            } catch (Exception e) {
                logger.error("定时任务创建失败，任务id：{}，任务参数：{}，错误信息：\n",scheduleTask.getId(),scheduleTask.getParam(),e);
                throw new MyCommonException("定时任务创建失败！请检查您输入的日期格式是否正确！");
            }
        } else {
            throw new MyCommonException("定时任务类型错误！");
        }
        return trigger;
    }







}
