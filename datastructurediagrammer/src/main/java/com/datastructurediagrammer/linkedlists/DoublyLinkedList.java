package com.datastructurediagrammer.linkedlists;

public class DoublyLinkedList<T extends Comparable<T>> {
    public DLLNode<T> head;
    public DLLNode<T> tail;

    public void prepend(T newData) {
        DLLNode<T> newNode = new DLLNode<T>(newData);
        newNode.next = head;
        head.previous = newNode;
        head = newNode;
        if (tail == null) { 
            tail = head;
        }
    }

    public void appendData(T newData) { 
        DLLNode<T> newNode = new DLLNode<T>(newData);

        append(newNode);
    }

    public void append(DLLNode<T> newNode) { 
        // Case 1: Empty list - point head and tail to new node
        if (head == null) { 
            head = newNode;
            tail = newNode;
            return;
        }
        // Case 2: Non-empty list - point tail.next to new node and tail to new node.
        if (head != null) { 
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
    }

    public int getLength() { 
        DLLNode<T> currentNode = head;
        int count = 0;
        while (currentNode != null) { 
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }

    public boolean isEmpty() { 
        return !(head != null);
    }

    public DLLNode<T> remove(DLLNode<T> existingNode) { 
        DLLNode<T> successor = existingNode.next;
        DLLNode<T> predecessor = existingNode.previous;

        if (successor != null) { 
            successor.previous = predecessor;
        }

        if (predecessor != null) { 
            predecessor.next = successor;
        }

        if (existingNode == head) { 
            head = successor;
        }

        if (existingNode == tail) { 
            tail = predecessor;
        }

        return existingNode;
    }
}