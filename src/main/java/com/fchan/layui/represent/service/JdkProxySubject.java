package com.fchan.layui.represent.service;

import com.fchan.layui.represent.service.impl.RealSubject;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JDK的动态代理,利用InvocationHandler生成代理类
 */

@Slf4j
public class JdkProxySubject implements InvocationHandler {


    private RealSubject realSubject;

    public JdkProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        log.info("开始执行动态代理的逻辑,类似spring aop的@Before通知");
        Object result = null;

        try {
            //利用反射调用目标对象的方法
            result = method.invoke(realSubject,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            //代理类不是真正处理的类,这里接着把异常往外面抛
            throw e;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw e;
        } finally {
            log.info("执行finally代码块,相当于springaop的@After通知");
        }

        return result;
    }
}
