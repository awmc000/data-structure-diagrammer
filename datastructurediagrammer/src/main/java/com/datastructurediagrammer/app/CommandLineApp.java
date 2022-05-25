package com.datastructurediagrammer.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.datastructurediagrammer.arrays.ArrayDiagrammer;

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
                    stringArrayOpsMenu(scanner, in, strArray, TypeChoice.STRING);
                    break;

                default:
                    break;
            }
        }

        return;
    }

    private static void integerArrayOpsMenu(Scanner scanner, Character input, Integer[] array, TypeChoice typeChoice) { 

    }

    private static void stringArrayOpsMenu(Scanner scanner, Character input, String[] array, TypeChoice typeChoice) { 

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
                /*
                 * case '2':
                 * break;
                 * case '3':
                 * break;
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
