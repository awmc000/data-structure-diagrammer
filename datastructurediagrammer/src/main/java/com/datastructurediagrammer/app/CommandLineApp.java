package com.datastructurediagrammer.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.datastructurediagrammer.arrays.ArrayDiagrammer;

public class CommandLineApp {

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
                    arrayOperationsMenu(scanner, in, intArray);
                    break;

                case '2':
                    String[] strArray = new String[10];
                    strArray[0] = "";
                    arrayOperationsMenu(scanner, in, strArray);
                    break;

                default:
                    break;
            }
        }

        return;
    }

    private static <T> void arrayOperationsMenu(Scanner scanner, char in, T[] array) {
        while (!(in == '0')) {
            System.out.println("What type of operation would you like to perform on your array? \n" +
                    "1. Assign a value at a given index\n" +
                    "2. Assign array with new array of different size\n" +
                    "3. Draw a diagram of the array\n" +
                    "4. Draw a series of diagrams of the array being sorted\n" +
                    "5. Write array contents to the console\n" +
                    "0. EXIT\n" +
                    "A. Repeat options");

            /*String input = scanner.next();
            if (input.length() > 0) {
                in = input.charAt(0);
            }*/
            in = getInput(scanner);
            switch (in) {
                case '1':
                    // If T is String
                    if (array[0].getClass().equals(String.class)) {
                        System.out.print("Please enter the string you wish to add: ");
                        T add = null;
                        boolean doneGettingString = false;
                        while (!doneGettingString) { 
                            try { 
                                add = (T) scanner.next();
                                doneGettingString = true;
                            } catch (InputMismatchException e) { 
                                // heckin
                                continue;
                            }
                            
                        }
                        
                        System.out.print("\nPlease enter the index of the array to which you wish to add it: ");
                        // When taking the desired index to add to, there are safeguards:
                        // 1. Negative input will be turned into positive
                        // 2. Modulo array size operation protects against numbers which are too high
                        int index = Math.abs(scanner.nextInt() % array.length);

                        // Perform the insertion
                        array[index] = add;
                    }
                    // If T is Integer
                    else if (array[0].getClass().equals(Integer.class)) {
                        System.out.print("Please enter the integer you wish to add: ");
                        Integer add = Integer.parseInt(scanner.next());
                        System.out.println("\nPlease enter the index of the array to which you wish to add it: ");
                        int index = Math.abs(scanner.nextInt() % array.length);

                        // Perform the insertion
                        array[index] = (T) add;
                    }
                    break;

                case '2':
                    // TODO: Set up new array of given size and copy over elements.

                    // Ask user for size desired.
                    System.out.print("Please enter the integer size you would like to resize the array to: ");

                    int chosenSize = scanner.nextInt();

                    if (chosenSize < 0) {
                        System.out.println("Cannot be negative.");
                    } else if (chosenSize > 100) {
                        System.out.println("Warning: Diagrams may be very large or generate improperly at this size.");
                    }

                    break;

                case '3':
                    // Generate diagram to given filepath.
                    System.out.print("\nPlease enter the desired title for the diagram: ");
                    String title = scanner.next();
                    System.out.print("Please enter the desired absolute filepath for the diagram: ");
                    String filepath = scanner.next();

                    ArrayDiagrammer<T> diagrammer = new ArrayDiagrammer<>();

                    System.out.println("To confirm you want to draw a diagram titled "
                            + title + " at the absolute filepath below, enter Y" + "\n" + filepath);

                    if (!(scanner.next().equals("Y"))) {
                        System.out.println("Diagram operation cancelled.");
                        break;
                    } else {
                        diagrammer.drawToFile(array, title, filepath);
                        System.out.println("Generated array diagram titled " + title
                                + ". The png file can be found at " + "\n" + filepath);
                    }
                    break;

                case '4':
                    break;

                case '5':
                    for (T val : array) {
                        if (val != null) {
                            System.out.print(val.toString() + " ");
                        }
                    }
                    System.out.println();

            }
        }

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
