# README Under Construction!
# Data Structure Diagrammer

## What it is

A command line tool to a.) generate diagrams of container data structures such as linked lists and trees and b.) generate series of diagrams, suitable for compiling to `.gif`, of a sorting operation carried out on a populated data container.

## Why it is useful

## How to use it

First, download a `.jar` file from the "releases" page of this repository.

### Generating a diagram of a binary search tree
Entered in the same directory as the `.jar` resides in, this command will generate a binary search tree containing the numbers 4, 3, 2, 1.
```
java -jar datastructurediagrammer-0.01.jar binarytree int 4 3 2 1
```

### Generating a diagram of a linked list

# How it works

## Technologies used
## Principles and design patterns used
Version numbers will be conformant to semantic versioning as described on https://semver.org/.

> Given a version number MAJOR.MINOR.PATCH, increment the:  
MAJOR version when you make incompatible API changes  
MINOR version when you add functionality in a backwards compatible manner  
PATCH version when you make backwards compatible bug fixes

## UML diagram

## Future goals

## Full repository map
In this tree representation of the repo dirs and files, `//` marks a comment as it would in the Java source code.
```
.
├── dataFX    // experimental JavaFX GUI for the Diagrammer, written by Zak Toews
│   └── fx
│       ├── pom.xml
│       ├── README.md
│       └── src
│          └── main
│              ├── java
│              │   ├── com
│              │   │   └── appfx
│              │   │       ├── ArrayDiagrammer.java
│              │   │       ├── BubbleSortArrayDiagrammer.java
│              │   │       ├── DiagramerApp.java
│              │   │       └── util
│              │   │           ├── BufferedImageFileWriter.java
│              │   │           ├── DataStructureDiagrammer.java
│              │   │           ├── DesignOptions.java
│              │   │           ├── TimeStamp.java
│              │   │           └── XYPair.java
│              │   └── module-info.java
│              └── resources
│                  └── com
│                      └── appfx
│                          ├── primary.fxml
│                          └── secondary.fxml
│       
└── datastructurediagrammer
    ├── 2023-01-07T20-47-52.950394 Bubble Sort of Numbers Diagram     // test results
    ├── pom.xml      // maven build configuration.
    ├── README.md
    ├── src
    │   ├── main
    │   │   └── java
    │   │       └── com
    │   │           └── datastructurediagrammer
    │   │               ├── App.java
    │   │               ├── arrays
    │   │               │   └── ArrayDiagrammer.java
    │   │               ├── challenges.md
    │   │               ├── linkedlists
    │   │               │   ├── DLLNode.java
    │   │               │   ├── DoublyLinkedListDiagrammer.java
    │   │               │   ├── DoublyLinkedList.java
    │   │               │   ├── SinglyLinkedListDiagrammer.java
    │   │               │   ├── SinglyLinkedList.java
    │   │               │   └── SLLNode.java
    │   │               ├── sorting
    │   │               │   ├── BubbleSortArrayDiagrammer.java
    │   │               │   ├── SelectionSortArrayDiagrammer.java
    │   │               │   └── SortDiagrammer.java
    │   │               ├── trees
    │   │               │   ├── AVLNode.java
    │   │               │   ├── AVLTreeDiagrammer.java
    │   │               │   ├── AVLTree.java
    │   │               │   ├── BinarySearchTree.java
    │   │               │   ├── BSTDiagrammer.java
    │   │               │   ├── BSTNode.java
    │   │               │   ├── LimitedBSTDiagrammer.java
    │   │               │   ├── Trie.java
    │   │               │   └── TrieNode.java
    │   │               └── util
    │   │                   ├── BufferedImageFileWriter.java
    │   │                   ├── DataStructureDiagrammer.java
    │   │                   ├── TimeStamp.java
    │   │                   └── XYPair.java
    │   └── test
    │       └── java
    │           └── com
    │               └── datastructurediagrammer
    │                   ├── AppTest.java
    │                   ├── sorting
    │                   │   └── BubbleSortArrayDiagrammerTest.java
    │                   └── trees
    │                       ├── AVLTreeTest.java
    │                       ├── BSTDiagrammer3Test.java
    │                       └── TrieTest.java
    └── target
        ├── classes
        │   └── com
        │       └── datastructurediagrammer
        │           ├── App.class
        │           ├── arrays
        │           │   └── ArrayDiagrammer.class
        │           ├── challenges.md
        │           ├── linkedlists
        │           │   ├── DLLNode.class
        │           │   ├── DoublyLinkedList.class
        │           │   ├── DoublyLinkedListDiagrammer.class
        │           │   ├── SinglyLinkedList.class
        │           │   ├── SinglyLinkedListDiagrammer.class
        │           │   └── SLLNode.class
        │           ├── sorting
        │           │   ├── BubbleSortArrayDiagrammer.class
        │           │   ├── SelectionSortArrayDiagrammer.class
        │           │   └── SortDiagrammer.class
        │           ├── trees
        │           │   ├── AVLNode$Child.class
        │           │   ├── AVLNode.class
        │           │   ├── AVLTree.class
        │           │   ├── AVLTreeDiagrammer.class
        │           │   ├── BinarySearchTree.class
        │           │   ├── BSTDiagrammer$NodeWrapper.class
        │           │   ├── BSTDiagrammer.class
        │           │   ├── BSTNode.class
        │           │   ├── LimitedBSTDiagrammer$NodeWrapper.class
        │           │   ├── LimitedBSTDiagrammer.class
        │           │   ├── Trie.class
        │           │   └── TrieNode.class
        │           └── util
        │               ├── BufferedImageFileWriter.class
        │               ├── DataStructureDiagrammer.class
        │               ├── TimeStamp.class
        │               └── XYPair.class
        ├── datastructurediagrammer-0.01.jar
        ├── generated-sources
        │   └── annotations
        ├── generated-test-sources
        │   └── test-annotations
        ├── maven-archiver
        │   └── pom.properties
        ├── maven-status
        │   └── maven-compiler-plugin
        │       ├── compile
        │       │   └── default-compile
        │       │       ├── createdFiles.lst
        │       │       └── inputFiles.lst
        │       └── testCompile
        │           └── default-testCompile
        │               ├── createdFiles.lst
        │               └── inputFiles.lst
        ├── surefire-reports
        │   ├── com.datastructurediagrammer.AppTest.txt
        │   ├── com.datastructurediagrammer.sorting.BubbleSortArrayDiagrammerTest.txt
        │   ├── com.datastructurediagrammer.trees.AVLTreeTest.txt
        │   ├── com.datastructurediagrammer.trees.BSTDiagrammer3Test.txt
        │   ├── com.datastructurediagrammer.trees.TrieTest.txt
        │   ├── TEST-com.datastructurediagrammer.AppTest.xml
        │   ├── TEST-com.datastructurediagrammer.sorting.BubbleSortArrayDiagrammerTest.xml
        │   ├── TEST-com.datastructurediagrammer.trees.AVLTreeTest.xml
        │   ├── TEST-com.datastructurediagrammer.trees.BSTDiagrammer3Test.xml
        │   └── TEST-com.datastructurediagrammer.trees.TrieTest.xml
        └── test-classes
            └── com
                └── datastructurediagrammer
                    ├── AppTest.class
                    ├── sorting
                    │   └── BubbleSortArrayDiagrammerTest.class
                    └── trees
                        ├── AVLTreeTest.class
                        ├── BSTDiagrammer3Test.class
                        └── TrieTest.class
```

## Credits
