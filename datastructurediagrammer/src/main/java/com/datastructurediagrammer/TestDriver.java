package com.datastructurediagrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

import java.time.LocalDateTime;

import com.datastructurediagrammer.arrays.ArrayDiagrammer;
import com.datastructurediagrammer.linkedlists.DoublyLinkedList;
import com.datastructurediagrammer.linkedlists.DoublyLinkedListDiagrammer;
//import com.datastructurediagrammer.linkedlists.DoublyLinkedListDiagrammer;
import com.datastructurediagrammer.linkedlists.SinglyLinkedList;
import com.datastructurediagrammer.linkedlists.SinglyLinkedListDiagrammer;
import com.datastructurediagrammer.sorting.BubbleSortArrayDiagrammer;
//import com.datastructurediagrammer.trees.BSTDiagrammer;
import com.datastructurediagrammer.trees.BSTDiagrammer3;
import com.datastructurediagrammer.trees.LimitedBSTDiagrammer;
import com.datastructurediagrammer.trees.BinarySearchTree;

public class TestDriver {
    public static void sllTest() throws FileNotFoundException { 
        System.out.println("Constructing list");
        SinglyLinkedList<String> sList = new SinglyLinkedList<String>("A");
        System.out.println("List constructed");
        
        // add data
        sList.appendData("B");
        sList.appendData("D");
        sList.insertAfterData(sList.head.next, "C");
        sList.prependData("S");
        sList.insertAfterData(sList.tail, "E");
        System.out.println("Elements added..");
        System.out.println("List: " + sList.toString());
        sList.print();
        File sllCheck = new File("sllCheck.txt");
        PrintWriter pw = new PrintWriter(sllCheck);
        pw.write(sList.toString());
        pw.close();
        System.out.println("Printed list");
        
        Random rand = new Random(4);

        SinglyLinkedList<Integer> intList = new SinglyLinkedList<Integer>();
        DoublyLinkedList<Integer> intDList = new DoublyLinkedList<Integer>();

        for (int i = 0; i < 100; ++i) { 
            intList.appendData(rand.nextInt(10000));
            intDList.appendData(rand.nextInt(100));
        }

        SinglyLinkedListDiagrammer.renderDiagram(sList, "String LL", "listDiagram.png");
        SinglyLinkedListDiagrammer.renderDiagram(intList, "Int LL", "intListDiagram.png");
        DoublyLinkedListDiagrammer<Integer> dlld = new DoublyLinkedListDiagrammer<>();
        dlld.renderDiagramFile(dlld.renderDiagram(intDList, "Integer doubly-linked list"), "refactored-diagrammer-doublelinkedlist.png");
    }
    /*
    public static void bstTest() { 
        //BinarySearchTree<String> stringTree = new BinarySearchTree<String>();
        Random rand = new Random();
        //String[] characters = {"a", "b", "c", "d", "e", "f"};
        //String generatedString;
        
        //System.out.println("printing:");
        //stringTree.printInOrder(stringTree.root);
        //System.out.println("height: " + stringTree.getHeight());
        BinarySearchTree<Integer> heightTree = new BinarySearchTree<>();
        BinarySearchTree<Integer> randomTree = new BinarySearchTree<>();
        int testCount = 40;
        for (int i = 0; i < testCount; ++i) {
            heightTree.insert(i);
            randomTree.insert(rand.nextInt(10000));
        }
        System.out.println(heightTree.getTotalNodes());
        String timeStamp = LocalDateTime.now().toString().replace(':', '-');
        BSTDiagrammer.renderDiagram(heightTree, timeStamp + "int_max_tree" + testCount + ".png");
        BSTDiagrammer.renderDiagram(randomTree, timeStamp + "random_int_tree" + testCount + ".png");
        System.out.println("saved");
        //BSTDiagrammer.renderDiagram(stringTree, "long_tree.png");
    } */
    public static void arrayTest() { 
        String timeStamp = LocalDateTime.now().toString().replace(':', '-');
        Integer[] intArray = {2, 4, 88, 9993, 234, 0, -1, 23, 4};
        ArrayDiagrammer<Integer> arrayDiagrammer = new ArrayDiagrammer<Integer>();
        arrayDiagrammer.drawToFile(intArray, "Integers", timeStamp + "int_array.png");
        String[] stringArray = {"Hello", "world", "object", "oriented", "java", "is", "interpreted", "AND", "compiled"};
        ArrayDiagrammer<String> stringDiagrammer = new ArrayDiagrammer<String>();
        stringDiagrammer.drawToFile(stringArray, "String", timeStamp + "string_array.png");
        BubbleSortArrayDiagrammer<Integer> bubbleSortArrayDiagrammer = new BubbleSortArrayDiagrammer<>();
        bubbleSortArrayDiagrammer.renderSortingOperation(intArray, "Int Array", timeStamp + "int_array_sorting.png");
    }

    public static void bstTest2() { 
        LimitedBSTDiagrammer<Integer> bstDiagrammer = new LimitedBSTDiagrammer<>();
        BinarySearchTree<Integer> intTree = new BinarySearchTree<>();
        

        int numbersToInsert = 16;
        Random rand = new Random();
        /*for (int i = 0; i < numbersToInsert; ++i) { 
            String timeStamp = LocalDateTime.now().toString().replace(':', '-');
            intTree.insert(rand.nextInt(100));
            bstDiagrammer.renderDiagram(intTree, 50, 50, "Integer BST", timeStamp + " - " + (i + 1) + " numbers - intTree.png");
        }*/

        intTree = new BinarySearchTree<>();
        numbersToInsert = 4;
        for (int i = 0; i < numbersToInsert; ++i) { 
            String timeStamp = LocalDateTime.now().toString().replace(':', '-');
            intTree.insert(rand.nextInt(100));
            bstDiagrammer.renderDiagram(intTree, 50, 50, "Integer BST", timeStamp + " - " + (i + 1) + " numbers - intTree.png");
        }
        /*
        intTree = new BinarySearchTree<>();
        numbersToInsert = 5;
        for (int i = 0; i < numbersToInsert; ++i) { 
            String timeStamp = LocalDateTime.now().toString().replace(':', '-');
            intTree.insert(rand.nextInt(100));
            bstDiagrammer.renderDiagram(intTree, 50, 50, "Integer BST", timeStamp + " - " + (i + 1) + " numbers - intTree.png");
        }

        intTree = new BinarySearchTree<>();
        numbersToInsert = 6;
        for (int i = 0; i < numbersToInsert; ++i) { 
            String timeStamp = LocalDateTime.now().toString().replace(':', '-');
            intTree.insert(rand.nextInt(100));
            bstDiagrammer.renderDiagram(intTree, 50, 50, "Integer BST", timeStamp + " - " + (i + 1) + " numbers - intTree.png");
        }

        intTree = new BinarySearchTree<>();
        numbersToInsert = 4;
        for (int i = 0; i < numbersToInsert; ++i) { 
            String timeStamp = LocalDateTime.now().toString().replace(':', '-');
            intTree.insert(rand.nextInt(100));
            bstDiagrammer.renderDiagram(intTree, 50, 50, "Integer BST", timeStamp + " - " + (i + 1) + " numbers - intTree.png");
        }*/
        
    }

    public static void bstTest3() { 
        BinarySearchTree<String> alphabetTree = new BinarySearchTree<>();
        String[] alphabetStrings = {"G", "B", "H", "E", "F", "I", "A", "ZZ", "W", "3", "HI"};
        alphabetTree.insert(alphabetStrings);

        Integer[] numbers = {2, 33, 5, 4, 1223, 532, 6664312, 002, 434, 54, -2333, 6543, 0, -0, 776, -776};//{1, 4, 3, 883, 20, 3805};
        BinarySearchTree<Integer> integerTree = new BinarySearchTree<>();
        integerTree.insert(numbers);

        BSTDiagrammer3<String> stringDiagrammer = new BSTDiagrammer3<>(alphabetTree);
        BSTDiagrammer3<Integer> integerDiagrammer = new BSTDiagrammer3<>(integerTree);

        stringDiagrammer.saveFile(alphabetTree, "Alphabet Tree Test", ts() + " Alphabet BST3 Test");
        //integerDiagrammer.saveFile(integerTree, "Integer Tree Test", ts() + " Integer BST3 Test");
    }
    
    public static void bubbleSortTest() { 
        Integer[] integerArr = {20, 18, 16, 14, 12, 10, 8, 6, 4, 2, 0};
        String[] stringArr = {"G", "F", "E", "D", "C", "B", "A", "AA"};

        BubbleSortArrayDiagrammer<Integer> integerSortDiagrammer = new BubbleSortArrayDiagrammer<>();
        BubbleSortArrayDiagrammer<String> stringSortDiagrammer = new BubbleSortArrayDiagrammer<>();
        integerSortDiagrammer.renderSortingOperation(integerArr, "Integer Array", "C:/Users/AMC/IdeaProjects/workshop-scratch-files/src/com/DataStructureDiagrammer/datastructurediagrammer/src/main/java/com/datastructurediagrammer");
        stringSortDiagrammer.renderSortingOperation(stringArr, "String Array", "C:/Users/AMC/IdeaProjects/workshop-scratch-files/src/com/DataStructureDiagrammer/datastructurediagrammer/src/main/java/com/datastructurediagrammer");
    }

    public static String ts() { 
        return LocalDateTime.now().toString().replace(':', '-');
    }

    public static void main(String[] args) {
        try { 
            //bstTest3();
            //sllTest();
            bubbleSortTest();
        } catch (Throwable t) {
            t.printStackTrace();
         }
    }
}
