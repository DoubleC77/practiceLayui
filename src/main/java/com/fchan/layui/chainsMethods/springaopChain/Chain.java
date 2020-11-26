package com.fchan.layui.chainsMethods.springaopChain;

import java.util.List;

public class Chain {

    List<ChainHandler> chainHandlers;

    private int index = 0;

    public Chain(List<ChainHandler> chainHandlers) {
        this.chainHandlers = chainHandlers;
    }

    public List<ChainHandler> getChainHandlers() {
        return chainHandlers;
    }

    public void setChainHandlers(List<ChainHandler> chainHandlers) {
        this.chainHandlers = chainHandlers;
    }

    protected void proceed() {
        if (index >= this.getChainHandlers().size()) {
            return;
        }
        this.getChainHandlers().get(index++).execution(this);
    }

}
