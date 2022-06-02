package com.datastructurediagrammer.linkedlists;

/** A doubly-linked list. Uses DLLNode.
 * @author Alex McColm
 */
public class DoublyLinkedList<T extends Comparable<T>> {
    // Members
    public DLLNode<T> head;
    public DLLNode<T> tail;

    // Default constructor
    public DoublyLinkedList() { 
        head = null;
        tail = null;
    }

    // Constructor with data for a root node
    public DoublyLinkedList(T initData) { 
        DLLNode<T> initNode = new DLLNode<T>(initData);
        head = initNode;
        tail = initNode;
    }

    /**
     * Constructs a node from given data, and puts it at the top of the list by a call to this.prepend().
     * @param newData
     */
    public void prependData(T newData) {
        DLLNode<T> newNode = new DLLNode<T>(newData);
        this.prepend(newNode);
    }

    /**
     * Puts given node at the top of the list.
     * @param newNode
     */
    public void prepend(DLLNode<T> newNode) { 
        newNode.next = head;
        head.previous = newNode;
        head = newNode;
        if (tail == null) { 
            tail = head;
        }
    }

    /**
     * Helper method.
     * Constructs a node from given data, and puts it at 
     * the end of the list by a call to this.append().
     * @param newData
     */
    public void appendData(T newData) { 
        DLLNode<T> newNode = new DLLNode<T>(newData);

        append(newNode);
    }

    /**
     * Puts the given node at the end of the list.
     * @param newNode
     */
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

    /**
     * Helper method for inserting a node after an existing node.
     * @param existingNode Existing node for new node to be inserted after.
     * @param newData Data for new node to be inserted after existing node.
     */
    public void insertAfterData(DLLNode<T> existingNode, T newData) { 
        DLLNode<T> newNode = new DLLNode<T>(newData);
        this.insertAfter(existingNode, newNode);
    }

    /**
     * Inserts a node after an existing node.
     * @param existingNode
     * @param newNode
     */
    public void insertAfter(DLLNode<T> existingNode, DLLNode<T> newNode) { 
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
    public DLLNode<T> search (T searchKey) { 
        DLLNode<T> currentNode = head;
        while (currentNode != null) { 
            if (currentNode.getData().compareTo(searchKey) == 0) { 
                return currentNode;
            }
        }
        return currentNode;
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


    /** Getter.
     * Return length by counting nodes with an accumulator.
     * @return int length of linked list
     */
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
