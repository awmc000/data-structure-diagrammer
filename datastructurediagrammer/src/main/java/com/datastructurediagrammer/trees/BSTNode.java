package com.datastructurediagrammer.trees;

/*
 * Node class for Binary Search Tree
 * 
 */
public class BSTNode<T extends Comparable<T>> {
    protected T data;
    
    // references to parent and child nodes
    public BSTNode<T> parent; // null if head
    public BSTNode<T> left;
    public BSTNode<T> right;

    /*
     * Constructor.
     * 
     * initially just sets data and does not touch references.
     * The references should be set on insertion.
     */
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
