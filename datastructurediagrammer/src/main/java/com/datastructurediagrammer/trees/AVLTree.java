package com.datastructurediagrammer.trees;

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
    }

    /**
     * Rebalances all the way to root node from given node.
     * @param node
     */
    private void rebalanceAncestors(AVLNode<T> node) {
        // Set up a node for upward traversal. 
        AVLNode<T> currentNode = node.parent;
        while (currentNode != null) {
            if (currentNode.parent != null) {
                // Rebalace the node above the one we are currently on.
                rebalance(currentNode.parent);
            }
            // Move upward to the next parent node.
            currentNode = currentNode.parent;
        }
    }

    /**
     * Performs a left AVL rotation around the given node.
     * 
     * @param a the node to rotate
     * @return the node which occupies that position after rotation
     */
    public AVLNode<T> rotateLeft(AVLNode<T> a) {
        AVLNode<T> p = a.parent;
        AVLNode<T> b = (AVLNode<T>) a.right;
        a.right = b.left;
        if (b.left != null) {
            b.left.parent = a;
        }
        b.left = a;
        a.parent = b;
        b.parent = p;
        if (p != null) {
            if (p.left == a) {
                p.left = b;
            } else {
                p.right = b;
            }
        }
        return b;
    }

    /**
     * Performs a right AVL rotation around the given node.
     * 
     * @param a the node to rotate
     * @return the node which occupies that position after rotation
     */
    public AVLNode<T> rotateRight(AVLNode<T> a) {
        AVLNode<T> p = a.parent;
        AVLNode<T> b = (AVLNode<T>) a.left;
        a.left = b.right;
        if (b.right != null) {
            b.right.parent = a;
        }
        b.right = a;
        a.parent = b;
        b.parent = p;
        if (p != null) {
            if (p.left == a) {
                p.left = b;
            } else {
                p.right = b;
            }
        }
        return b;
    }

    /**
     * Perform rotations to fix the AVL tree.
     * 
     * @param node root of subtree to rebalance
     * @return root of subtree now rebalanced
     */
    public AVLNode<T> rebalance(AVLNode<T> node) {
        node.updateHeight();
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
