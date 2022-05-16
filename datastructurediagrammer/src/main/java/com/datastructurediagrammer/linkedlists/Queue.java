package com.datastructurediagrammer.linkedlists;

public class Queue<T extends Comparable<T>> {
    private DoublyLinkedList<T> doublyLinkedList;

    public void enqueueData(T newData) { 
        DLLNode<T> newNode = new DLLNode<T>(newData);
        doublyLinkedList.append(newNode);
    }

    public DLLNode<T> dequeue() { 
        return doublyLinkedList.remove(doublyLinkedList.head);
    }

    public T dequeueData() { 
        return dequeue().getData();
    }

    public DoublyLinkedList<T> getList() { 
        return doublyLinkedList;
    }
}
