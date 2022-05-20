package com.datastructurediagrammer.trees;

public class AVLNode <T extends Comparable<T>> extends BSTNode<T> {
    private int height;

    public AVLNode(T initData) { 
        super(initData);
        height = 0;
    }

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

    public int getBalance() { 
        int[] heights = getChildHeights();

        // Return balance factor
        return heights[0] - heights[1];
    }

    public void updateHeight() { 
        int[] heights = getChildHeights();
        height = (Math.max(heights[0], heights[1]));
    }

    //TODO: implement setChild, replaceChild, rotateLeft, rotateRight, rebalance methods
}
