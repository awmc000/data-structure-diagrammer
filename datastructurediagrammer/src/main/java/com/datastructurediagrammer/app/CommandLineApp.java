package com.datastructurediagrammer.app;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.datastructurediagrammer.arrays.ArrayDiagrammer;
import com.datastructurediagrammer.linkedlists.SinglyLinkedList;
import com.datastructurediagrammer.sorting.BubbleSortArrayDiagrammer;

public class CommandLineApp {

    enum TypeChoice { 
        STRING,
        INTEGER
    }

    private static char getInput(Scanner scanner) {
        String input = scanner.next();

        if (input.length() > 0) {
            return input.charAt(0);
        } else {
            return 'Z';
        }
    }

    //TODO: implement integer linked list operations
    private static void integerLinkedListOpsMenu(Scanner scanner, char in, SinglyLinkedList<Integer> list) { 

    }

    private static void linkedListSetupMenu(Scanner scanner, char in, boolean doublyLinked) { 
        while (in != '0') { 
            System.out.println("What type of data would you like your linked list to contain?\n" + 
            "1. Integer\n" + 
            "2. String\n" +
            "0. EXIT \n " +
            "A. Repeat options");
            in = getInput(scanner);

            // TODO: Implement options
            switch (in) { 
                case '1':
                    integerLinkedListOpsMenu(scanner, in, new SinglyLinkedList<Integer>());
                    break;

                case '2':
                    break;

                case '3':
                    break;

                case '4':
                    break;
            }
        }
    }

    private static void arraySetupMenu(Scanner scanner, char in) {

        while (!(in == '0')) {
            System.out.println("What type would you like your array to contain?\n" +
                    "1. Integer [starting length 10]\n" +
                    "2. String  [starting length 10]\n" +
                    "0. EXIT\n" +
                    "A. Repeat options");
            
            /*String input = scanner.next();
            if (input.length() > 0) {
                in = input.charAt(0);
            }*/
            in = getInput(scanner);

            switch (in) {
                case '1':
                    Integer[] intArray = new Integer[10];
                    intArray[0] = Integer.valueOf(-0);
                    integerArrayOpsMenu(scanner, in, intArray, TypeChoice.INTEGER);
                    break;

                case '2':
                    String[] strArray = new String[10];
                    strArray[0] = "";
                    stringArrayOpsMenu(scanner, in, strArray);
                    break;

                default:
                    break;
            }
        }

        return;
    }

    private static void integerArrayOpsMenu(Scanner scanner, Character input, Integer[] array, TypeChoice typeChoice) { 
        while (input != '0') {
            System.out.println("What type of operation would you like to perform on your array? \n" +
            "1. Assign a value at a given index\n" +
            "2. Assign array with new array of different size (truncating or padding w/ null)\n" +
            "3. Draw a diagram of the array to PNG\n" +
            "4. Draw a series of diagrams (PNG) of the array being sorted\n" +
            "5. Write array contents to the console\n" +
            "0. EXIT\n" +
            "A. Repeat options");
            input = getInput(scanner);

            switch (input) { 
                // User chooses to set an element.
                case '1':
                    System.out.print("Enter the index of the array to wish you which to assign: ");
                    int index = Integer.parseInt(scanner.next());
                    System.out.print("\nEnter the value you wish to insert at array " + index + ": ");
                    String value = scanner.next();
                    /*if (typeChoice.equals(TypeChoice.STRING)) { 
                        array[index] = value;
                    } else*/ if (typeChoice.equals(TypeChoice.INTEGER)) { 
                        array[index] = Integer.parseInt(value);
                    }
                    System.out.println();
                    break;

                // User chooses to resize integer array.
                case '2':
                    System.out.print("Enter the new size for the array to be resized to: ");
                    // A Java library method handles the copying for us.
                    Integer[] newArr = Arrays.copyOf(array, scanner.nextInt());
                    array = newArr;
                    break;

                // User chooses to draw a diagram.
                case '3':
                    System.out.print("Enter the absolute filepath to which you want to save your diagram:");
                    String filepathChoice = scanner.next();
                    System.out.print("\nEnter the title for your diagram: ");
                    String titleChoice = scanner.next();
                    System.out.println();
                    ArrayDiagrammer<Integer> diagrammer = new ArrayDiagrammer<>();
                    diagrammer.drawToFile(array, titleChoice, filepathChoice);
                    break;

                // User chooses to render a sorting operation.
                case '4':
                    System.out.println("Currently, only bubble sort is available!");
                    System.out.print("Enter the absolute directory path to which you want to save your diagrams:");
                    filepathChoice = scanner.next();
                    System.out.print("\nEnter the title for your diagram: ");
                    titleChoice = scanner.next();
                    System.out.println();
                    BubbleSortArrayDiagrammer<Integer> sortDiagrammer = new BubbleSortArrayDiagrammer<>();
                    sortDiagrammer.renderSortingOperation(array, titleChoice, filepathChoice);
                    break;

                // User chooses to write array contents to console.
                case '5':
                    for (int i = 0; i < array.length; i++) {
                        if (array[i] != null) {
                            System.out.print(i + ": " + array[i]);
                        }
                    }
                    System.out.println();

            }
        }
    }

    private static void stringArrayOpsMenu(Scanner scanner, Character input, String[] array) { 
        System.out.println("What type of operation would you like to perform on your array? \n" +
        "1. Assign a value at a given index\n" +
        "2. Assign array with new array of different size\n" +
        "3. Draw a diagram of the array\n" +
        "4. Draw a series of diagrams of the array being sorted\n" +
        "5. Write array contents to the console\n" +
        "0. EXIT\n" +
        "A. Repeat options");
        input = getInput(scanner);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char in = 'Z';
        while (!(in == '0')) {
            System.out.println("What type of data structure would you like to create?\n" +
                    "1. Array\n" +
                    "2. Singly-Linked List\n" +
                    "3. Doubly-Linked List\n" +
                    "4. Stack\n" +
                    "5. Queue\n" +
                    "6. Binary Search Tree\n" +
                    "7. AVL Tree [SOON]\n" +
                    "8. Red-Black Tree\n" +
                    "9. TBD \n" +
                    "0. EXIT\n" +
                    "A. Repeat options");
            /*String input = scanner.next();
            if (input.length() > 0) {
                in = input.charAt(0);
            }*/
            in = getInput(scanner);
            switch (in) {
                case '1':
                    arraySetupMenu(scanner, in);
                    break;
                
                case '2':
                    linkedListSetupMenu(scanner, in, false);
                    break;
                
                case '3':
                    linkedListSetupMenu(scanner, in, false);
                    break;
                /*
                 * case '4':
                 * break;
                 * case '5':
                 * break;
                 * case '6':
                 * break;
                 * case '7':
                 * break;
                 * case '8':
                 * break;
                 * case '9':
                 * break;
                 * case '0':
                 * break;
                 * case 'A':
                 * break;
                 * default:
                 * break;
                 */
            }
        }
        scanner.close();
    }
}
