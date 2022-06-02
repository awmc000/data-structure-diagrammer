package com.datastructurediagrammer.linkedlists;

/** A singly-linked list. Uses SLLNode.
 * @author Alex McColm
 */
public class SinglyLinkedList<T extends Comparable<T>> {
    // Members
    public SLLNode<T> head;
    public SLLNode<T> tail;

    // Default constructor
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

    /**
     * Helper method.
     * Constructs a node from given data, and puts it at 
     * the top of the list by a call to this.prepend().
     * @param newData
     */
    public void prependData(T newData) {
        SLLNode<T> newNode = new SLLNode<T>(newData);
        this.prepend(newNode);
    }

    /**
     * Puts given node at the top of the list.
     * @param newNode
     */
    public void prepend(SLLNode<T> newNode) { 
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

    /**
     * Helper method.
     * Constructs a node from given data, and puts it at 
     * the end of the list by a call to this.append().
     * @param newData
     */
    public void appendData(T newData) { 
        SLLNode<T> newNode = new SLLNode<T>(newData);
        this.append(newNode);
    }

    /**
     * Puts the given node at the end of the list.
     * @param newNode
     */
    public void append(SLLNode<T> newNode) { 
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

    /**
     * Helper method for inserting a node after an existing node.
     * @param existingNode Existing node for new node to be inserted after.
     * @param newData Data for new node to be inserted after existing node.
     */
    public void insertAfterData(SLLNode<T> existingNode, T newData) { 
        SLLNode<T> newNode = new SLLNode<T>(newData);
        this.insertAfter(existingNode, newNode);
    }

    /**
     * Inserts a node after an existing node.
     * @param existingNode
     * @param newNode
     */
    public void insertAfter(SLLNode<T> existingNode, SLLNode<T> newNode) { 
        if (existingNode != null) {
            newNode.next = existingNode.next;
            existingNode.next = newNode;
        } else {
            this.append(newNode);
        }
    }

    /**
     * If a match exists, returns the first node with a data key
     * matching the search key.
     * @param searchKey
     * @return SLLNode whose key matches search key.
     */
    public SLLNode<T> search (T searchKey) { 
        SLLNode<T> currentNode = head;
        while (currentNode != null) { 
            if (currentNode.getData().compareTo(searchKey) == 0) { 
                return currentNode;
            }
        }
        return currentNode;
    }

    // TODO: Rewrite the removeAfter method.
    /**
     * TODO - NOT IMPLEMENTED.
     * @param existingNode
     * @return
     */
    public boolean removeAfter (SLLNode<T> existingNode) { 
        return false;
    }


    /** Getter.
     * Return length by counting nodes with an accumulator.
     * @return int length of linked list
     */
    public int getLength() { 
        SLLNode<T> currentNode = head;
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
