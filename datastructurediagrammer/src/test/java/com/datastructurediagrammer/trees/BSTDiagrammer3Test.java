package com.datastructurediagrammer.trees;

import static org.junit.Assert.assertTrue;

//import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import com.datastructurediagrammer.util.TimeStamp;

import org.junit.Test;

public class BSTDiagrammer3Test {
    
    public void makeNumberTrees(int numbers, int bound) { 
        BinarySearchTree<Integer> bigTree = new BinarySearchTree<>();
        Random rand = new Random(3);
        ArrayList<Integer> dupeCheck = new ArrayList<>();
        int next;
        for (int i = 0; i < numbers; i++) { 
            next = rand.nextInt(bound);
            
            if (!dupeCheck.contains(next)) { 
                bigTree.insert(next);
                dupeCheck.add(next);
            }
        }

        BSTDiagrammer3<Integer> treeDiagrammer = new BSTDiagrammer3<>(bigTree);
        treeDiagrammer.saveFile(bigTree, numbers + " Number Tree", System.getProperty("user.dir") + TimeStamp.ts() + " " + numbers + " Numbers Tree.png");
    }

    public void makeAlpahebtTrees(int number) { 

    }

    @Test
    public void avlTreeTest() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        Random rand = new Random(3);
        for (int i = 0; i < 20; i++) { 
            avlTree.insert(rand.nextInt(1000));
        }
        BSTDiagrammer3<Integer> treeDiagrammer = new BSTDiagrammer3<>(avlTree);
        treeDiagrammer.saveFile(avlTree, "AVL Tree Test", "C:/Users/AMC/Desktop/AVL bstdiagrammer3 test/AVL Tree Test.png");
        assertTrue(true);
    }

    @Test
    public void numberTreesTest() { 
        // Powers of 3 from 3^1 to 3^5, ie. 3 to 243, bound of 1000
        /*for (int i = 1; i < 6; ++i) { 
            makeNumberTrees((int) Math.pow(3, i), 1000);
        }*/

        // Powers of 4 from 4^1 to 4^5, bound of 1000
        for (int i = 1; i < 3; ++i) { 
            makeNumberTrees((int) Math.pow(4, i), 1000);
        }
    }
}
