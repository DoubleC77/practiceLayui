package com.fchan.layui.springLife;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringFactoryDemo implements FactoryBean {

    @Autowired
    private GetSpringCreateBeanName getSpringCreateBeanName;


    /**
     * 这里可以自己制造一个JDK动态代理对象方法spring单例池中
     * 制造的是SpringFactoryDemo的实现类
     * <p>
     * 和懒加载的bean一样,在使用的时候(比如第一次使用applicationContext.getbean获取这个bean)才会使用这里这个getObject方法提供的bean的类型,不过此时单例池中是这个SpringFactoryDemo bean自己的类型
     * 中间会先去 factorybeanChache里去找,找不到这个bean才会到这个方法中去获取自己产生返回的代理对象
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object getObject() throws Exception {
        //这里可以自己注入jdk动态代理的对象
        return new SpringFactoryDemoSon();
    }


    /**
     * 返回自己要注入的bean的class
     *
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        //return SpringLife.class;
        return SpringFactoryDemoSon.class;
    }


}
