package com.datastructurediagrammer.trees;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    public AVLTree() {
        super();
    }

    public AVLTree(AVLNode<T> initRoot) { 
        super(initRoot);
    }
    
    //TODO: implement rotateLeft
    public void rotateLeft(AVLNode<T> node) { 

    }
    
    //TODO: implement rotateRight 
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

    //TODO: implement rebalance
}
