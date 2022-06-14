package com.datastructurediagrammer.trees;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests for the generic AVL Tree implementation.
 */
public class AVLTreeTest {
    @Test
    public void placementTest() {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] testNums = { 1, 15, 12, 13 };
        for (Integer num : testNums) {
            tree.insert(new AVLNode<Integer>(num));
        }
        tree.printInOrder(tree.root);
        System.out.println("ROOT: " + tree.root.data.toString());
        // A rotation should have occurred, so we assert that the root should not be 1.
        assertTrue(tree.root.data.intValue() != 1);
    }
    @Test 
    public void increasingNumsTest() { 
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 0; i < 20; i++) { 
            tree.insert(new AVLNode<Integer>(i));
        }
        System.out.println("ROOT: " + tree.root.data.toString());
        assertTrue(tree.root.data.intValue() != 0);
        
    }
    public static void main (String[] args) { 
        AVLTreeTest test = new AVLTreeTest();
        test.increasingNumsTest();
    }
}
