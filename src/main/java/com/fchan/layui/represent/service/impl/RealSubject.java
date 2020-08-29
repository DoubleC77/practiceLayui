package com.fchan.layui.represent.service.impl;

import com.fchan.layui.represent.service.Subject;

public class RealSubject implements Subject {
    @Override
    public void print() {
        System.out.println("被代理的对象(即真实对象)的print方法");
    }
}
