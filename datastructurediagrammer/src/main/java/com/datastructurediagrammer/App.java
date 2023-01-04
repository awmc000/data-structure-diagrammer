package com.datastructurediagrammer;

import com.datastructurediagrammer.arrays.ArrayDiagrammer;
import com.datastructurediagrammer.trees.BSTDiagrammer;
import com.datastructurediagrammer.trees.BinarySearchTree;
import com.datastructurediagrammer.trees.Trie;
import com.datastructurediagrammer.util.TimeStamp;

/*
 * The App class contains static methods to run the command line 
 * interface for the Data Structure Diagrammer.
 */
public class App {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Not enough arguments!");
        } else {
            // input should consist of a data structure's keyword, a data type, and then 1+
            // strings to be its elements
            String structure = args[0];
            switch (structure) {
                case "array":
                    makeArray(args);
                    break;
                case "binarytree":
                    makeBinaryTree(args);
                    break;
                case "trie":
                    // TODO
                    break;
                default:
                    System.out.println("Not a valid option or data structure not supported.");
            }
        }
    }

    /*
     * makeArray()
     * 
     * Menu. User has elected to make an array. We need to see what 
     * type they chose, and populate the array with the elements they gave.
     */
    public static void makeArray(String[] args) {
        // Grab the type selected - should be "int" or "string"
        String typeSelected = args[1];
        
        // Get the number of elements to be placed in the array
        int numElems = args.length - 2; // first is data structure's keyword, second is data type, remainder are elems
        
        String filename = TimeStamp.ts();

        if (typeSelected.equalsIgnoreCase("int")) {
            
            // initialize array
            Integer[] intArr = new Integer[numElems]; // a wrapper class is used for int
            
            // parse elements and add to array
            for (int i = 2; i < args.length; i++) { // starts at 2 because first 2 args are container type and element type
                intArr[i - 2] = Integer.parseInt(args[i]);
            }
            
            // initialize diagrammer
            ArrayDiagrammer<Integer> diagrammer = new ArrayDiagrammer<>();
            
            System.out.println("Generating int array diagram.");
            
            // draw the diagram to a file in the working directory
            filename += " Int Array Diagram.png";
            
            diagrammer.drawToFile(intArr, "Int Array Diagram", filename);
            
            System.out.println("Created diagram at: " + System.getProperty("user.dir") + filename);
        } else if (typeSelected.equalsIgnoreCase("String")) {
            
            // initialize array
            String[] stringArr = new String[numElems];

            // no parsing needed, these are already strings
            for (int i = 2; i < args.length; i++) {
                stringArr[i - 2] = args[i];
            }
            
            // initialize diagrammer
            ArrayDiagrammer<String> diagrammer = new ArrayDiagrammer<>();
            
            filename += " String Array Diagram.png";

            // draw diagram to file
            diagrammer.drawToFile(stringArr, "String Array Diagram", filename);
            
            System.out.println("Created diagram at: " + System.getProperty("user.dir") + filename);
        } else { // User did not enter "int" or "string"
            System.out.println("Types supported are 'int' and 'string'.");
        }
    }

    public static void makeBinaryTree(String[] args) {
        // Grab the type selected - should be "int" or "string"
        String typeSelected = args[1];
                
        String filename = TimeStamp.ts();

        if (typeSelected.equalsIgnoreCase("int")) {
            BinarySearchTree<Integer> intTree = new BinarySearchTree<>();

            // parse elements and add to array
            for (int i = 2; i < args.length; i++) { // starts at 2 because first 2 args are container type and element type
                intTree.insert(Integer.parseInt(args[i]));
            }

            BSTDiagrammer<Integer> diagrammer = new BSTDiagrammer<>(intTree);
            filename += " Int Binary Search Tree";

            diagrammer.saveFile(intTree, "Int Binary Search Tree", filename);

            System.out.println("Created diagram at: " + System.getProperty("user.dir") + filename);
        }
        else if (typeSelected.equalsIgnoreCase("string")) {
            BinarySearchTree<String> stringTree = new BinarySearchTree<>();

            // parse elements and add to array
            for (int i = 2; i < args.length; i++) { // starts at 2 because first 2 args are container type and element type
                stringTree.insert(args[i]);
            }

            BSTDiagrammer<String> diagrammer = new BSTDiagrammer<>(stringTree);
            filename += " String Binary Search Tree";

            diagrammer.saveFile(stringTree, "String Binary Search Tree", filename);

            System.out.println("Created diagram at: " + System.getProperty("user.dir") + filename);
        } else {
            System.out.println("Types supported are 'int' and 'string'.");
        }
    }

    public static void makeTrie(String[] args) {
        String typeSelected = args[1];

        if (!typeSelected.equalsIgnoreCase("string")) {
            System.out.println("Only String type is supported for Tries.");
            return;
        }

        Trie trie = new Trie();

        for (int i = 2; i < args.length; i++)
        {
            trie.add(args[i]);
        }

        System.out.println("Trie has been set up, but cannot be diagrammed yet.");
    }

}
