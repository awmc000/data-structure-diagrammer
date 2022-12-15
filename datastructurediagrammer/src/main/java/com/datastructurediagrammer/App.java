package com.datastructurediagrammer;

import com.datastructurediagrammer.arrays.ArrayDiagrammer;

/**
 * Hello world!
 *
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

    public static void makeArray(String[] args) {
        String typeSelected = args[1];
        int numElems = args.length - 2; // first is data structure's keyword, second is data type, remainder are elems
        if (typeSelected.equalsIgnoreCase("int")) {
            Integer[] intArr = new Integer[numElems];
            for (int i = 2; i < args.length; i++) {
                intArr[i - 2] = Integer.parseInt(args[i]);
                System.out.println("Parsed and added " + Integer.parseInt(args[i]) + " to array.");
            }
            ArrayDiagrammer<Integer> diagrammer = new ArrayDiagrammer<>();
            System.out.println("Generating int array diagram.");
            diagrammer.drawToFile(intArr, "Int Array Diagram", System.currentTimeMillis() + " Int Array Diagram.png");
        } else if (typeSelected.equalsIgnoreCase("String")) {
            String[] stringArr = new String[numElems];
            for (int i = 2; i < args.length; i++) {
                stringArr[i - 2] = args[i];
            }
            ArrayDiagrammer<String> diagrammer = new ArrayDiagrammer<>();
            diagrammer.drawToFile(stringArr, "String Array Diagram", " String Array Diagram.png");
        } else {
            System.out.println("Types supported are 'int' and 'string'. ");
        }
    }
}
