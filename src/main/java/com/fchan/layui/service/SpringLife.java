package com.fchan.layui.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class SpringLife implements BeanNameAware, InitializingBean, BeanFactoryPostProcessor {


    private String beanName;


    /**
     * BeanNameAware接口
     * 在spring实例化bean的时候获取bean的name
     *
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }


    /**
     * InitializingBean接口
     * 初始化bean
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

    }


    /**
     * BeanFactoryPostProcessor 接口---后置处理器
     * 修改当前bean的实例化的bean的类型
     * <p>
     * beanFactory(组件完成)---->BeanFactoryPostProcessor(后置处理器)
     * <p>
     * beanDefinition扫描所有的bean,加载到beanDefinitionMap后之后说明beanFactory组件完了
     * <p>
     * applicationContext是间接继承了beanFactory接口,前者功能稍微多一点
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanFactory.getBean("beanDefinition");

        //System.out.println(beanDefinition.getBeanClass());


        //beanFactory可以获取bean,创建bean,注册bean
        /*beanFactory.getBean();
        beanFactory.createBean();       //会让spring实例化bena,自动注入属性
        beanFactory.registerSingleton();    //直接放到单例池中,spring不会自动注入属性
        */


        //可以修改这个bean的实现类型
        //beanDefinition.setBeanClass(XXX.class);

    }
}
