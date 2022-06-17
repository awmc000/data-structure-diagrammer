package com.datastructurediagrammer.trees;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.ArrayList;

import com.datastructurediagrammer.util.BufferedImageFileWriter;
import com.datastructurediagrammer.util.DataStructureDiagrammer;

/**
 * BST Diagrammer which should be more effective than the LimitedBSTDiagrammer.
 * Draws an "image" of the BST to a 2 dimensional arraylist and then draws to
 * BufferedImage
 * based on that image.
 * 
 * in the "image" 2 dimensional ArrayList each list contains wrapped nodes with
 * added members
 * which can be edited or accessed to help draw them to the image.
 */
public class BSTDiagrammer3<T extends Comparable<T>> implements DataStructureDiagrammer<BinarySearchTree<T>> {
    /**
     * NodeWrapper as the name suggests is a wrapper class for BSTNode objects that
     * adds some more information
     * relevant to drawing the diagram.
     */
    class NodeWrapper {
        // The node itself which is wrapped.
        public BSTNode<T> node;
        // Pointer to the parent, used to draw a line.
        public NodeWrapper parent;
        public int level;
        // These x and y coordinates (pixels from top left) are the coordinates at
        // which the node has been drawn, 0,0 by default but then assigned their
        // location.
        public int x;
        public int y;

        public int width;

        // NodeWrapper constructor takes the node to wrap and the level it is on
        public NodeWrapper(BSTNode<T> newNode, int level) {
            node = newNode;
            parent = null;
            this.level = level;
            this.x = 0;
            this.y = 0;
            this.width = 0;
        }
    }

    // Two dimensional ArrayList of NodeWrapper type.
    private ArrayList<ArrayList<NodeWrapper>> treeImage;
    // Horizontal buffer
    public int hBuffer;

    // Vertical buffer
    public int vBuffer;
    public int levelHeight;
    public int nodeWidth;

    /**
     * Constructor
     * 
     * @param tree The binary search tree to be diagrammed.
     */
    public BSTDiagrammer3(BinarySearchTree<T> tree) {
        // Set up variables
        treeImage = new ArrayList<ArrayList<NodeWrapper>>();
        hBuffer = 40;
        vBuffer = 40;
        levelHeight = 20;
        nodeWidth = 20;

        // Set up the image
        this.imagineTree(tree);
    }

    /**
     * imagineTree() takes a BST and assigns a completed "image" of it
     * to the diagrammer's image data member. Each node is wrapped with diagramming
     * info
     * and put in a 2d ArrayList as mentioned in the class description.
     * 
     * @param tree the BST to "imagine"
     */
    private void imagineTree(BinarySearchTree<T> tree) {
        // Add a column to the 2d ArrayList for each level of the BST.
        for (int i = 0; i < tree.getHeight(); ++i) {
            treeImage.add(new ArrayList<NodeWrapper>());
        }
        // First, manually add the root to the first column.
        NodeWrapper rootNode = new NodeWrapper(tree.root, 1);
        treeImage.get(0).add(rootNode);

        // Recursively wrap each node and store in the treeImage.
        wrapNodes(treeImage, rootNode, 1, rootNode);
    }

    /**
     * Recursive method to put nodes in a wrapper class with additional
     * diagramming info.
     * 
     * @param wrapperArray The 2 dimensional ArrayList of NodeWrapper type in which
     *                     wrappers are to be stored.
     * @param currentNode  The current (wrapped!) node being worked on.
     * @param level        The current level of the tree being worked on. (Where
     *                     root level is 0, increasing going down when visualized)
     * @param parent       The parent node of the current node - null for root.
     */
    private void wrapNodes(ArrayList<ArrayList<NodeWrapper>> wrapperArray, NodeWrapper currentNode, int level,
            NodeWrapper parent) {
        System.out.println("Node [" + currentNode.node.getData().toString() + "] has left child: "
                + (currentNode.node.left != null));
        if (currentNode.node.left != null) {
            NodeWrapper leftChild = new NodeWrapper(currentNode.node.left, level + 1);
            leftChild.parent = currentNode;
            wrapperArray.get(level).add(leftChild);
            wrapNodes(wrapperArray, leftChild, level + 1, currentNode);
        }

        // Wrap current node if root

        // If wrapping root.
        /*
         * if ((currentNode.level == 0) && (!(currentNode.parent == currentNode))) {
         * NodeWrapper rootNode = new NodeWrapper(currentNode.node, level);
         * wrapperArray.get(0).add(rootNode);
         * }
         */
        System.out.println("Node [" + currentNode.node.getData().toString() + "] has right child: "
                + (currentNode.node.right != null));

        if (currentNode.node.right != null) {
            NodeWrapper rightChild = new NodeWrapper(currentNode.node.right, level + 1);
            rightChild.parent = currentNode;
            wrapperArray.get(level).add(rightChild);
            wrapNodes(wrapperArray, rightChild, level + 1, currentNode);
        }
    }

    /**
     * Used for testing. Writes the flattened image of the BST to the console.
     */
    public void printImage() {
        for (int i = 0; i < treeImage.size(); ++i) {
            for (int j = 0; j < treeImage.get(i).size(); ++j) {

                String parentData = "";

                if (i > 0) {
                    parentData = treeImage.get(i).get(j).parent.node.getData().toString();
                }
                System.out.println(treeImage.get(i).get(j).node.getData().toString() + " level "
                        + treeImage.get(i).get(j).level + " parent " + parentData);
            }
            System.out.println();
        }
    }

    public BufferedImage renderDiagram(BinarySearchTree<T> tree, String title) {
        // Image dimensions start as placeholder 1x1.
        int imageWidth = 1;
        int imageHeight = 1;

        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics;

        int treeHeight = tree.getHeight();

        imageHeight = levelHeight * treeHeight + (2 * vBuffer) + 200;

        // Most nodes on any given level. This should help us determine width of the
        // image.
        int maxLevelNodes = 0;

        for (int i = 0; i < treeImage.size(); ++i) {
            if (treeImage.get(i).size() > maxLevelNodes) {
                maxLevelNodes = treeImage.get(i).size();
            }
        }

        imageWidth = (hBuffer * 2) + (maxLevelNodes * 4 * nodeWidth) + (20 * nodeWidth);

        bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        graphics = (Graphics2D) bufferedImage.getGraphics();

        FontMetrics fontMetrics = graphics.getFontMetrics();

        int titleX = (imageWidth / 2) - (fontMetrics.stringWidth(title) / 2);
        int titleY = vBuffer / 2;

        setupImage(graphics, imageWidth, imageHeight, title, titleX, titleY);

        int elemX, elemY, xIncrement, nodesOnCurrLevel;

        // Work through the image, writing nodes.
        for (int i = 0; i < treeImage.size(); ++i) {

            elemY = ((levelHeight) * 2 * (i + 1)) + 60;
            System.out.println("ElemY = " + elemY);
            nodesOnCurrLevel = treeImage.get(i).size();

            for (int j = 0; j < nodesOnCurrLevel; ++j) {
                NodeWrapper currWrapper = treeImage.get(i).get(j);
                /*
                 * To determine the node width:
                 * - Get the width in pixels of the node data when written as a string
                 * - Multiply by two, since the data text will be centred.
                 * - Adds a few px just to be sure.
                 */
                nodeWidth = fontMetrics.stringWidth(currWrapper.node.getData().toString()) * 2 + 5;

                // TODO: Improve the way the element's x-coordinate is determined

                /*
                 * The horizontal increment: We want each node on the level to be evenly spaced.
                 * This is currently determined by dividing width by nodes on current level.
                 * So for a level with 3 nodes, they are incremented by 1/3rd of the width.
                 */
                xIncrement = (imageWidth / (nodesOnCurrLevel));

                // The element's x-pos will be its position following the increment
                elemX = xIncrement * (j);
                // plus half the buffer as a bit of an offset
                elemX += (hBuffer / 2);

                // If the node is not root
                if (currWrapper.parent != null) {
                    // Nudge it to the left if it is a left child
                    if (currWrapper.parent.node.left == currWrapper.node) {
                        elemX -= nodeWidth;
                    } else { // Or nudge it to the right if it is a right child.
                        elemX += nodeWidth;
                    }
                }

                // TODO: Do not remember what this additional x-nudge does, or if it really
                // helps. We could axe it.
                elemX += (xIncrement / 2);

                // If there's only one node on the level, centre it horizontally.
                if (nodesOnCurrLevel == 1) {
                    elemX = imageWidth / 2;
                }

                drawNode(graphics, currWrapper, elemX, elemY);
            }
        }

        return bufferedImage;
    }

    /**
     * Hidden helper method to set up the blank image of determined size, and write the title
     * at top centre.
     * @param graphics
     * @param imageWidth
     * @param imageHeight
     * @param title
     * @param titleX
     * @param titleY
     */
    private void setupImage(Graphics2D graphics, int imageWidth, int imageHeight, String title, int titleX,
            int titleY) {
        // Fill with white background
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        // Write title
        graphics.setColor(Color.BLACK);
        graphics.drawString(title, titleX, titleY);
    }

    /**
     * Hidden helper method to draw each node with the data in its NodeWrapper.
     * @param graphics
     * @param currWrapper
     * @param elemX
     * @param elemY
     */
    protected void drawNode(Graphics2D graphics, NodeWrapper currWrapper, int elemX, int elemY) {
        // Add the (x,y) position and width to the wrapper so children nodes can access
        // it.
        currWrapper.x = elemX;
        currWrapper.y = elemY;
        currWrapper.width = nodeWidth;

        System.out.println("ElemX = " + elemX);

        // Draw the rectangle node
        graphics.drawRect(elemX, elemY, nodeWidth, levelHeight);

        // Write the data inside the node
        graphics.drawString(currWrapper.node.getData().toString(), elemX + 5, elemY + levelHeight / 2 + 2);

        // Set up variables for drawing line to parent
        int topCentre, parentX;

        // Draw a line to the parent!
        if (currWrapper.parent != null) {
            // The centre of the top of the current node: its x-value plus half a node
            // width.
            topCentre = currWrapper.x + (nodeWidth / 2);

            if (currWrapper.x != currWrapper.parent.x) {
                if (currWrapper.parent.node.left == currWrapper.node) {
                    parentX = currWrapper.parent.x + (currWrapper.parent.width / 2);
                } else {
                    parentX = currWrapper.parent.x; // - (currWrapper.parent.width / 2);
                }
            } else {
                parentX = currWrapper.parent.x;
            }

            graphics.drawLine(topCentre, currWrapper.y, parentX, currWrapper.parent.y + levelHeight);
        }
    }

    /**
     * Helper method which takes a BST, a title, and an absolute path, renders a
     * diagram
     * and writes to the filepath given.
     * 
     * @param tree     the BST to be drawn in the diagram.
     * @param title    the title to be written on the diagram.
     * @param filepath the absolute path to which to save the diagram
     */
    public void saveFile(BinarySearchTree<T> tree, String title, String filepath) {
        BufferedImageFileWriter.writeToFile(this.renderDiagram(tree, title), filepath);
    }

}
