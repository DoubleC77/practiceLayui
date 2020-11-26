package com.fchan.layui.chainsMethods.springaopChain;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

public class ChainClient {

    static class ChainHandlerA extends ChainHandler {

        @Override
        protected void handlerProcess() {
            System.out.println("chain handlerA");
        }
    }

    static class ChainHandlerB extends ChainHandler {

        @Override
        protected void handlerProcess() {
            System.out.println("chain handlerB");
        }
    }

    static class ChainHandlerC extends ChainHandler {

        @Override
        protected void handlerProcess() {
            System.out.println("chain handlerC");
        }
    }

    public static void main(String[] args) {
        /*List<ChainHandler> list = Arrays.asList(
                new ChainHandlerA(),
                new ChainHandlerB(),
                new ChainHandlerC()
        );
        Chain chain = new Chain(list);
        chain.proceed();
        do{
            System.out.println(111);
        }while (true);*/


        System.out.println(1000 == 1000);
    }


}
