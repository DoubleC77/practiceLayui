package com.fchan.layui.quzrtzForSpring;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class QuzrtzForSpringConfig {

    @Bean
    public JobDetail myJobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(HiJob.class)
                //任务名和任务组别
                .withIdentity("myJobName", "myJobGroup")
                //JobDataMap可以给任务execute传递参数
                .usingJobData("job_param", "job_param1")
                .storeDurably()
                .build();
        return jobDetail;
    }

    @Bean
    public Trigger myTrigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                //触发器名,触发器所在组
                .withIdentity("myTrigger1", "myTriggerGroup1")
                .usingJobData("job_trigger_param", "job_trigger_param1")
                .startNow()
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ? 2020"))
                .build();
        return trigger;
    }

}
