package com.fchan.layui.represent.service;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDemoInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib represent before");
        Object result = null;
        try {
            result = methodProxy.invokeSuper(o, objects);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        } finally {
            System.out.println("cglib represent finally");
        }
        return result;
    }
}
