package com.fchan.layui.springLife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 拿到创建当前这个bean的beanFactory
 */
@Component
public class GetSpringBeanFactory implements BeanFactoryAware {

    private BeanFactory beanFactory;

    ApplicationContext applicationContext;

    @Autowired
    GetSpringCreateBeanName getSpringCreateBeanName;


    public void test() {
        GetSpringCreateBeanName test = (GetSpringCreateBeanName) beanFactory.getBean("getSpringCreateBeanName");
        System.out.println(test == getSpringCreateBeanName);
        test.toString();
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
