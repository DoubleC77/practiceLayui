package com.fchan.layui.quartz;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestJobDetailService {


    public String doJob() {
        System.out.println("开始处理业务了");
        return LocalDateTime.now().toString();
    }

}
