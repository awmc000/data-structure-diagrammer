package com.datastructurediagrammer.linkedlists;

public class SLLNode<U extends Comparable<U>> {
    private U data;
    public SLLNode<U> next;

    SLLNode(U initData) {
        data = initData;
        next = null;
    }

    public void setData(U newData) { 
        data = newData;
    }

    public U getData() {
        return data;
    }

}
