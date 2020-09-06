package com.fchan.layui.chainsMethods;

public abstract class Handler {

    private Handler successor;

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public void handlerProcess(){
        execution();
        if(null != successor){
            successor.handlerProcess();
        }
    }

    protected abstract void execution();

}
