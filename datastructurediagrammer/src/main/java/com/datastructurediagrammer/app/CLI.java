package com.datastructurediagrammer.app;

import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char in = 'A';
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
            "9. TBD \n");
            in = scanner.next().charAt(0);
        }
        scanner.close();
    }
}
