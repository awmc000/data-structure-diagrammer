package com.datastructurediagrammer.trees;

import java.util.ArrayList;


/**
 * A very open-ended Tree. It has no invariant property and nodes can have any amount of children.
 */
public class Tree <T> {
    class TreeNode {
        public T data;
        public ArrayList<TreeNode> children;
    }
    public TreeNode root;
}
