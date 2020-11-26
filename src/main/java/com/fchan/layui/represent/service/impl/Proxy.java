package com.fchan.layui.represent.service.impl;

import com.fchan.layui.represent.service.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Proxy implements Subject {

    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void print() {
        log.info("代理对象开始干活了");
        //被代理的对象处理业务
        realSubject.print();
        log.info("代理对象干活结束");
    }
}
