package com.fchan.layui.chainsMethods;

public class Client {

    static class HandlerA extends Handler {

        @Override
        public void execution() {
            System.out.println("HandlerA");
        }
    }

    static class HandlerB extends Handler {

        @Override
        public void execution() {
            System.out.println("HandlerB");
        }
    }

    static class HandlerC extends Handler {

        @Override
        public void execution() {
            System.out.println("HandlerC");
        }
    }


    public static void main(String[] args) {
        Handler handlerA = new HandlerA();
        Handler handlerB = new HandlerB();
        Handler handlerC = new HandlerC();

        handlerA.setSuccessor(handlerB);
        handlerB.setSuccessor(handlerC);

        handlerA.handlerProcess();
    }


}
