package com.datastructurediagrammer.linkedlists;

public class SinglyLinkedList<T extends Comparable<T>> {
    // Data members
    public SLLNode<T> head;
    public SLLNode<T> tail;

    // default constructor
    public SinglyLinkedList() { 
        head = null; 
        tail = null;
    }

    // Constructor with data for a root node
    public SinglyLinkedList(T initData) {
        SLLNode<T> initNode = new SLLNode<T>(initData);
        head = initNode;
        tail = initNode;
    }

    public void prependData(T newData) {
        SLLNode<T> newNode = new SLLNode<T>(newData);

        // Case 1: Empty list - point head and tail to new node
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        // Case 2: Non-empty list - point head to new node and head.next to old head
        if (head != null) { 
            newNode.next = head;
            head = newNode;
            // The tail will still be correct.
        }
    }

    public void appendData(T newData) { 
        SLLNode<T> newNode = new SLLNode<T>(newData);

        // Case 1: Empty list - point head and tail to new node
        if (head == null) { 
            head = newNode;
            tail = newNode;
            return;
        }
        // Case 2: Non-empty list - point tail.next to new node and tail to new node.
        if (head != null) { 
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void insertAfterData(SLLNode<T> existingNode, T newData) { 
        SLLNode<T> newNode = new SLLNode<T>(newData);
        
        if (existingNode != null) {
            newNode.next = existingNode.next;
            existingNode.next = newNode;
        } else {
            this.appendData(newData);
        }
    }

    public SLLNode<T> search (T searchKey) { 
        SLLNode<T> currentNode = head;
        while (currentNode != null) { 
            if (currentNode.getData().compareTo(searchKey) == 0) { 
                return currentNode;
            }
        }
        return currentNode;
    }

    public boolean removeAfter(SLLNode<T> existingNode) { 
        // If given null, remove head
        if (existingNode == null && head != null) {
            SLLNode<T> succeedingNode = head.next;
            head = succeedingNode;
            if (succeedingNode != null) { 
                tail = null;
            }
        } else if (existingNode.next != null) {
            SLLNode<T> succeedingNode = existingNode.next.next;
            existingNode.next = succeedingNode;
            if (succeedingNode == null) { 
                tail = null;
            }
        }
        return true;
    }

    public int getLength() { 
        SLLNode<T> currentNode = head;
        int count = 0;
        while (currentNode != null) { 
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }

    public String toString() { 
        String listString = "";
        SLLNode<T> currentNode = head;
        while (currentNode != null) { 
            listString += currentNode.getData().toString();
            if (currentNode.next != null) { 
                listString += " -> ";
            }
            // Infinite loop
            currentNode = currentNode.next;
        }
        return listString;
    }
    
    public void print() { 
        System.out.println(this.toString());
    }

}
