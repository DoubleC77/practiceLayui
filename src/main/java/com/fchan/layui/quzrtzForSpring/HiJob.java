package com.fchan.layui.quzrtzForSpring;

import com.fchan.layui.quartz.TestJobDetailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class HiJob extends QuartzJobBean {

    @Autowired
    private TestJobDetailService testJobDetailService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //可以获取JobDetail处传递过来的参数
        System.out.println(context.getMergedJobDataMap());
        testJobDetailService.doJob();
        System.out.println("    Hi! :" + context.getJobDetail().getKey());
    }
}
