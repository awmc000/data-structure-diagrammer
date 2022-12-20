package com.datastructurediagrammer;

import com.datastructurediagrammer.arrays.ArrayDiagrammer;
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
                case "Array":
                    makeArray(args);
                    break;
                case "BinaryTree":
                    // TODO
                    break;
                case "Trie":
                    // TODO
                    break;
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
        
        if (typeSelected.equalsIgnoreCase("int")) {
            
            // initialize array
            Integer[] intArr = new Integer[numElems]; // a wrapper class is used for int
            
            // parse elements and add to array
            for (int i = 2; i < args.length; i++) { // starts at 2 because first 2 args are container type and element type
                intArr[i - 2] = Integer.parseInt(args[i]);
                System.out.println("Parsed and added " + Integer.parseInt(args[i]) + " to array.");
            }
            
            // initialize diagrammer
            ArrayDiagrammer<Integer> diagrammer = new ArrayDiagrammer<>();
            
            System.out.println("Generating int array diagram.");
            
            // draw the diagram to a file in the working directory
            diagrammer.drawToFile(intArr, "Int Array Diagram", TimeStamp.ts() + " Int Array Diagram.png");

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
            diagrammer.drawToFile(stringArr, "String Array Diagram", " String Array Diagram.png");
        
        } else { // User did not enter "int" or "string"
            System.out.println("Types supported are 'int' and 'string'.");
        }
    }
}
