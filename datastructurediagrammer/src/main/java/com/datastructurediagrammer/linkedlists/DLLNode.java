package com.datastructurediagrammer.linkedlists;

/**
 * Node for the doubly-linked list.
 * @author Alex McColm
 */
public class DLLNode <T extends Comparable<T>> {
    /**
     * 
     * @param initData
     */
    DLLNode(T initData) {
        this.data = initData;
    }

    public DLLNode<T> previous;
    public DLLNode<T> next;
    public T data;

    /**
     * Data getter for the doubly linked list node.
     * @return Node's data content.
     */
    public T getData() { 
        return data;
    }
}