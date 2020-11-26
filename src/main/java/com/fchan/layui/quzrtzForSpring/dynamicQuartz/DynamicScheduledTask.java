package com.fchan.layui.quzrtzForSpring.dynamicQuartz;

import com.fchan.layui.quartz.TestJobDetailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class DynamicScheduledTask implements SchedulingConfigurer {

    private String cron = "0/3 * * * * ?";

    @Autowired
    private TestJobDetailService testJobDetailService;

    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.addTriggerTask(() -> {
            //定时任务的逻辑
            testJobDetailService.doJob();
        }, (TriggerContext triggerContext) -> {
            CronTrigger cronTrigger = new CronTrigger(cron);
            Date nextExecutionTime = cronTrigger.nextExecutionTime(triggerContext);
            return nextExecutionTime;
        });
        this.scheduledTaskRegistrar = scheduledTaskRegistrar;
    }

    public void destory() {
        if (null != this.scheduledTaskRegistrar) {
            this.scheduledTaskRegistrar.destroy();
        }
    }


    public void deleteTrigger(TriggerTask triggerTask) {
        this.scheduledTaskRegistrar.getTriggerTaskList().remove(triggerTask);
    }


}
