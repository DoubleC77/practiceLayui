package com.fchan.layui.chainsMethods.springaopChain;

public abstract class ChainHandler {


    public void execution(Chain chain){
        handlerProcess();
        chain.proceed();
    }

    protected abstract void handlerProcess();

}
