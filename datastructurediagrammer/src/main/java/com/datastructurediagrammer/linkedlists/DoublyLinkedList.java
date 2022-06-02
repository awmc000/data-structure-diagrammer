package com.datastructurediagrammer.linkedlists;

public class DoublyLinkedList<T extends Comparable<T>> {
    public DLLNode<T> head;
    public DLLNode<T> tail;

    public void prependData(T newData) {
        DLLNode<T> newNode = new DLLNode<T>(newData);
        this.prepend(newNode);
    }

    public void prepend(DLLNode<T> newNode) { 
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
            currentNode = (DLLNode<T>) currentNode.next;
        }
        return count;
    }

    public boolean isEmpty() { 
        return !(head != null);
    }

    public DLLNode<T> remove(DLLNode<T> existingNode) { 
        DLLNode<T> successor = (DLLNode<T>) existingNode.next;
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

    @Override
    public String toString() { 
        String ret = "";
        DLLNode<T> currNode = this.head;
        while (currNode != null) { 
            ret += currNode.toString();
            if (currNode.next != null) { 
                ret += " -> ";
            }
        }
        return ret;
    }
}
