package com.datastructurediagrammer;

import com.datastructurediagrammer.arrays.ArrayDiagrammer;
import com.datastructurediagrammer.linkedlists.SinglyLinkedList;
import com.datastructurediagrammer.linkedlists.SinglyLinkedListDiagrammer;
import com.datastructurediagrammer.sorting.BubbleSortArrayDiagrammer;
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
        }
        
        // If the user gave "help" as an argument, print some information
        // about how to use the tool.
        if (args[0].equalsIgnoreCase("help")) {
            System.out.println("Enter arguments as follows: [data structure] [data type] [elements]\n" +
                    "Example: java -jar *.jar array int 1 2 4 8 16 32 64\n" +
                    "Available data structures: array binarytree bubblesort sll");
        }

        // Input should consist of a data structure's keyword, a data type, and then 1+
        // strings to be its elements
        String structure = args[0];
        String typeSelected = args[1];
        int numElems = args.length - 2;
        switch (structure) {
            case "array":
                makeArray(args, typeSelected, numElems);
                break;

            case "binarytree":
                makeBinaryTree(args, typeSelected);
                break;

            case "trie":
                makeTrie(args, typeSelected);
                break;

            case "bubblesort":
                makeBubbleSort(args, typeSelected, numElems);
                break;

            case "sll":
                makeSinglyLinkedList(args, typeSelected);
                break;
            default:
                System.out.println("Not a valid option or data structure not supported.");
        }
    }

    private static void makeBubbleSort(String[] args, String typeSelected, int numElems) {
        // Grab the type selected - should be "int" or "string"
        //String typeSelected = args[1];
        
        // Get the number of elements to be placed in the array
        //int numElems = args.length - 2; // first is data structure's keyword, second is data type, remainder are elems

        if (typeSelected.equalsIgnoreCase("int")) {
            
            // initialize array
            Integer[] intArr = new Integer[numElems]; // a wrapper class is used for int
            
            // parse elements and add to array
            for (int i = 2; i < args.length; i++) { // starts at 2 because first 2 args are container type and element type
                intArr[i - 2] = Integer.parseInt(args[i]);
            }

            // initialize diagrammer
            BubbleSortArrayDiagrammer<Integer> diagrammer = new BubbleSortArrayDiagrammer<>();

            diagrammer.renderSortingOperation(intArr, "integer array", TimeStamp.ts());

        } else if (typeSelected.equalsIgnoreCase("String")) {
            
            // initialize array
            String[] stringArr = new String[numElems];

            // no parsing needed, these are already strings
            for (int i = 2; i < args.length; i++) {
                stringArr[i - 2] = args[i];
            }
            
            // initialize diagrammer
            BubbleSortArrayDiagrammer<String> diagrammer = new BubbleSortArrayDiagrammer<>();

            diagrammer.renderSortingOperation(stringArr, "string array", TimeStamp.ts());

        }
    }

    public static void makeSinglyLinkedList(String[] args, String typeSelected) {

        if (typeSelected.equalsIgnoreCase("int")) {
            SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();

            for (int i = 2; i < args.length; i++) {
                linkedList.appendData(Integer.parseInt(args[i]));
            }

            SinglyLinkedListDiagrammer.renderDiagram(linkedList, "int sll", TimeStamp.ts());

        } else if (typeSelected.equalsIgnoreCase("String")) {

            SinglyLinkedList<String> linkedList = new SinglyLinkedList<>();

            for (int i = 2; i < args.length; i++) {
                linkedList.appendData(args[i]);
            }

            SinglyLinkedListDiagrammer.renderDiagram(linkedList, "string sll", TimeStamp.ts());

        } else {
            System.out.println("Only int and String types are supported.");
        }
    }

    /*
     * makeArray()
     * 
     * Menu. User has elected to make an array. We need to see what
     * type they chose, and populate the array with the elements they gave.
     */
    public static void makeArray(String[] args, String typeSelected, int numElems) {


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
            
            diagrammer.drawToFile(intArr, "Int Array Diagram", TimeStamp.ts());
            
            System.out.println("Created diagram at: " + System.getProperty("user.dir") + TimeStamp.ts());
        } else if (typeSelected.equalsIgnoreCase("String")) {
            
            // initialize array
            String[] stringArr = new String[numElems];

            // no parsing needed, these are already strings
            for (int i = 2; i < args.length; i++) {
                stringArr[i - 2] = args[i];
            }
            
            // initialize diagrammer
            ArrayDiagrammer<String> diagrammer = new ArrayDiagrammer<>();

            // draw diagram to file
            diagrammer.drawToFile(stringArr, "String Array Diagram", TimeStamp.ts());
            
            System.out.println("Created diagram at: " + System.getProperty("user.dir") + TimeStamp.ts());
        } else { // User did not enter "int" or "string"
            System.out.println("Types supported are 'int' and 'string'.");
        }
    }

    public static void makeBinaryTree(String[] args, String typeSelected) {

        if (typeSelected.equalsIgnoreCase("int")) {
            BinarySearchTree<Integer> intTree = new BinarySearchTree<>();

            // parse elements and add to array
            for (int i = 2; i < args.length; i++) { // starts at 2 because first 2 args are container type and element
                                                    // type
                intTree.insert(Integer.parseInt(args[i]));
            }

            BSTDiagrammer<Integer> diagrammer = new BSTDiagrammer<>(intTree);

            diagrammer.saveFile(intTree, "Int Binary Search Tree", TimeStamp.ts());

            System.out.println("Created diagram at: " + System.getProperty("user.dir") + TimeStamp.ts());
        } else if (typeSelected.equalsIgnoreCase("string")) {
            BinarySearchTree<String> stringTree = new BinarySearchTree<>();

            // parse elements and add to array
            for (int i = 2; i < args.length; i++) { // starts at 2 because first 2 args are container type and element
                                                    // type
                stringTree.insert(args[i]);
            }

            BSTDiagrammer<String> diagrammer = new BSTDiagrammer<>(stringTree);

            diagrammer.saveFile(stringTree, "String Binary Search Tree", TimeStamp.ts());

            System.out.println("Created diagram at: " + System.getProperty("user.dir") + TimeStamp.ts());
        } else {
            System.out.println("Types supported are 'int' and 'string'.");
        }
    }

    public static void makeTrie(String[] args, String typeSelected) {

        if (!typeSelected.equalsIgnoreCase("string")) {
            System.out.println("Only String type is supported for Tries.");
            return;
        }

        Trie trie = new Trie();

        for (int i = 2; i < args.length; i++) {
            trie.add(args[i]);
        }

        System.out.println("Trie has been set up, but cannot be diagrammed yet.");
    }

}
