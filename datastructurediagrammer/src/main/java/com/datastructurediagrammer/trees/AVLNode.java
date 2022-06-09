package com.datastructurediagrammer.trees;

/**
 * Generic AVL node.
 */
public class AVLNode <T extends Comparable<T>> extends BSTNode<T> {
    public enum Child {
        LEFT,
        RIGHT
    }
    
    // Data, parent, left & right are in superclass BSTNode<T>
    private int height;
    public AVLNode<T> parent;

    public AVLNode(T initData) { 
        super(initData);
        height = 0;
    }

    /**
     * Gets heights of child nodes of this node.
     * @return size 2 int[] containing height of left and right
     * subtree of this node.
     */
    private int[] getChildHeights() { 
        int[] results = new int[2];
        // Get height of left subtree, or -1 if null
        int leftHeight = -1;
        if (this.left != null) { 
            AVLNode<T> leftNode = (AVLNode<T>) left;
            leftHeight = leftNode.height;
        }
        results[0] = leftHeight;

        // Get height of right subtree, or -1 if null.
        int rightHeight = -1;
        if (this.right != null) { 
            AVLNode<T> rightNode = (AVLNode<T>) right;
            rightHeight = rightNode.height;
        }
        results[1] = rightHeight;

        return results;
    }

    /**
     * Returns int balance factor of this node.
     * @return
     */
    public int getBalance() { 
        int[] heights = getChildHeights();

        // Return balance factor
        return heights[0] - heights[1];
    }

    /**
     * Updates height.
     */
    public void updateHeight() { 
        int[] heights = getChildHeights();
        height = (Math.max(heights[0], heights[1]));
    }

    /**
     * Set the child of an AVL node.
     * @param whichChild
     * @param newChild
     * @return success
     */
    public boolean setChild(Child whichChild, AVLNode<T> newChild) { 
        if ((whichChild != Child.LEFT) && (whichChild != Child.RIGHT)) {
            return false;
        }

        if (whichChild == Child.LEFT) { 
            this.left = newChild;
        } else if (whichChild == Child.RIGHT) {
            this.right = newChild;
        }

        if (newChild != null) { 
            newChild.parent = this;
        }
        return true;
    }

    /**
     * Replace one of the children of an AVL node.
     * @param currentChild
     * @param newChild
     * @return success
     */
    public boolean replaceChild(AVLNode<T> currentChild, AVLNode<T> newChild) {
        if (this.left == currentChild) { 
            return setChild(Child.LEFT, newChild);
        } else if (this.right == currentChild) { 
            return setChild(Child.RIGHT, newChild);
        }
        return false;
    }
}
