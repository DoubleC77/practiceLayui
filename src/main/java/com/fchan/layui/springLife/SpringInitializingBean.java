package com.fchan.layui.springLife;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringInitializingBean implements InitializingBean {

    private String userName;

    @Autowired
    private SpringBeanFactoryPostProcessor springBeanFactoryPostProcessor;

    @PostConstruct
    void init() {
        System.out.println("PostConstruct");
    }

    /**
     * bean的初始化
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.userName = "XXXXX";
    }
}
