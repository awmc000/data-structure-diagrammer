package com.datastructurediagrammer.linkedlists;

/**
 * Node for the doubly-linked list.
 * @author Alex McColm
 */
public class DLLNode <T extends Comparable<T>> extends SLLNode<T> {
    /**
     * 
     * @param initData
     */
    DLLNode(T initData) {
        super(initData);
    }

    public DLLNode<T> previous;
    //public DLLNode<T> next;
    //public T data;
}