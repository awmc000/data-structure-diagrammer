package com.datastructurediagrammer.trees;

public class BSTNode<T extends Comparable<T>> {
    private T data;
    public BSTNode<T> parent;
    public BSTNode<T> left;
    public BSTNode<T> right;

    public BSTNode(T initData) { 
        data = initData;
    }

    public T getData() { 
        return data;
    }

    public void setData(T newData) { 
        this.data = newData;
    }

    public int compareTo(BSTNode<T> otherNode) {
        return data.compareTo(otherNode.getData());
    }
}
