package com.fchan.layui.springLife;

import com.fchan.layui.service.TestDesi;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;


@Component
public class SpringBeanFactoryPostProcessor implements BeanFactoryPostProcessor, BeanFactoryAware {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        /*GenericBeanDefinition springFactoryDemo = (GenericBeanDefinition) beanFactory.getBeanDefinition("springFactoryDemo");
        springFactoryDemo.setBeanClass(SpringFactoryDemoSon.class);
        System.out.println(springFactoryDemo.getBeanClassName());*/

        //强制修改当前 SpringBeanFactoryPostProcessor bean注入进spring的类型
        //springFactoryDemo.setBeanClassName(SpringInitializingBean.class.getName());

        /*beanFactory.registerSingleton("xxx",new GetSpringBeanFactory());

        //通过手动设置by type注入属性的方式,意味着这个bean中的属性可以不用@Autowired注解也可以by type自动注入
        //不过要让这种模式生效,要让 testDesi 和 testDesi中的属性类都要被spring扫描到才行
        GenericBeanDefinition testDesi = (GenericBeanDefinition) beanFactory.getBeanDefinition("testDesi");
        testDesi.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
        TestDesi testDesi1 = (TestDesi) beanFactory.getBean("testDesi");
        testDesi1.test();*/

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("beanFactory");
    }
}
