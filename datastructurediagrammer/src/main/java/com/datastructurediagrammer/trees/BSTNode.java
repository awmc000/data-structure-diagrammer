package com.datastructurediagrammer.trees;

public class BSTNode<T extends Comparable<T>> {
    protected T data;
    public BSTNode<T> parent;
    public BSTNode<T> left;
    public BSTNode<T> right;

    public BSTNode(T initData) { 
        data = initData;
        parent = null;
        left = null;
        right = null;
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
