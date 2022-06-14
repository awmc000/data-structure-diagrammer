package com.datastructurediagrammer.trees;

import com.datastructurediagrammer.trees.AVLNode.Child;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    public AVLTree() {
        super();
    }

    public AVLTree(AVLNode<T> initRoot) {
        super(initRoot);
    }

    /**
     * BST insertion algorithm modified to account for parent pointers and rebalance ancestors after insertion.
     * @param newNode
     */
    public void insert(AVLNode<T> newNode) {
        AVLNode<T> currentNode = (AVLNode<T>) root;
        if (root == null) {
            root = newNode;
            newNode.parent = null;
            return;
        }
        while (currentNode != null) {
            if (newNode.compareTo(currentNode) < 0) {
                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    newNode.parent = currentNode;
                    currentNode = null;
                } else {
                    currentNode = (AVLNode<T>) currentNode.left;
                }
            } else {
                if (currentNode.right == null) {
                    currentNode.right = newNode;
                    newNode.parent = currentNode;
                    currentNode = null;
                } else {
                    currentNode = (AVLNode<T>) currentNode.right;
                }
            }
        }
        rebalanceAncestors(newNode);
        System.err.println("Inserted " + newNode.data + " into AVL tree.");
        System.err.println("Rebalancing ancestors...");
    }

    /**
     * Rebalances all the way to root node from given node.
     * @param node
     */
    private void rebalanceAncestors(AVLNode<T> node) {
        // Set up a node for upward traversal. 
        AVLNode<T> currentNode = (AVLNode<T>) node.parent;
        System.err.println("Rebalancing - Current node: " + currentNode.data.toString());
        while (currentNode != null) {
            if (currentNode.parent != null) {
                // Rebalace the node above the one we are currently on.
                System.err.println("next rebalance - Parent node: " + currentNode.parent.data.toString());
                rebalance((AVLNode<T>) currentNode.parent);
            }
            // Move upward to the next parent node.
            currentNode = (AVLNode<T>) currentNode.parent;
        }
    }

    /**
     * Performs a left AVL rotation around the given node.
     * 
     * @param a the node to rotate
     * @return the node which occupies that position after rotation
     */
    public AVLNode<T> rotateLeft(AVLNode<T> a) {
        // Convenience pointer to left child of right child of a.
        AVLNode<T> rightLeftChild = (AVLNode<T>) a.right.left;

        // Step 1 - Right child moves up to a's position.
        // Node is temporarily detached from tree. Will be re-attached
        if (a.parent != null) { 
            ((AVLNode<T>) a.parent).replaceChild(a,(AVLNode<T>)a.right);
        } else { // node is root
            root = a.right;
            root.parent = null;
        }

        // Step 2 - the node becomes the left child of what used
        // to be the node's right child, is now node's parent.
        ((AVLNode<T>) a.right).setChild(Child.LEFT, a);

        // Step 3 - reattach rightLeftChild as right child of node
        a.setChild(Child.RIGHT, rightLeftChild);

        return (AVLNode<T>) a.parent;
    }

    /**
     * Performs a right AVL rotation around the given node.
     * 
     * @param a the node to rotate
     * @return the node which occupies that position after rotation
     */
    public AVLNode<T> rotateRight(AVLNode<T> a) {
        AVLNode<T> leftRightChild = (AVLNode<T> )a.left.right;

        if (a.parent != null) {
            ((AVLNode<T>) a.parent).replaceChild(a,(AVLNode<T>)a.left);
        } else { 
            root = a.left;
            root.parent = null;
        }

        ((AVLNode<T>) a.left).setChild(Child.RIGHT, a);

        a.setChild(Child.LEFT, leftRightChild);

        return (AVLNode<T>) a.parent;
    }

    /**
     * Perform rotations to fix the AVL tree.
     * 
     * @param node root of subtree to rebalance
     * @return root of subtree now rebalanced
     */
    public AVLNode<T> rebalance(AVLNode<T> node) {
        node.updateHeight();
        System.err.println("Current node: " + node.data.toString() + ". Balance factor = " + node.getBalance());
        if (node.getBalance() == -2) {
            if (((AVLNode<T>) node.right).getBalance() == 1) {
                rotateRight((AVLNode<T>) node.right);
            }
            return rotateLeft((AVLNode<T>) node);
        } else if (node.getBalance() == 2) {
            if (((AVLNode<T>) node.left).getBalance() == -1) {
                rotateLeft((AVLNode<T>) node.left);
            }
            return rotateRight((AVLNode<T>) node);
        }
        return node;
    }

}
