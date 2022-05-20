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
 * Draws an "image" of the BST to a 2 dimensional arraylist and then draws to BufferedImage
 * based on that image.
 */
public class BSTDiagrammer3<T extends Comparable<T>> implements DataStructureDiagrammer<BinarySearchTree<T>> {
    class NodeWrapper { 
        public BSTNode<T> node;
        public NodeWrapper parent;
        public int level;
        public int x;
        public int y;
        public int width;

        public NodeWrapper(BSTNode<T> newNode, int level) { 
            node = newNode;
            parent = null;
            this.level = level;
            this.x = 0;
            this.y = 0;
            this.width = 0;
        }
    }

    private ArrayList<ArrayList<NodeWrapper>> treeImage;
    public int hBuffer;
    public int vBuffer;
    public int levelHeight;
    public int nodeWidth;

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

    private void imagineTree(BinarySearchTree<T> tree) { 
        for (int i = 0; i < tree.getHeight(); ++i) { 
            treeImage.add(new ArrayList<NodeWrapper>());
        }
        NodeWrapper rootNode = new NodeWrapper(tree.root, 1);
        treeImage.get(0).add(rootNode);
        wrapNodes(treeImage, rootNode, 1, rootNode);
    }

    private void wrapNodes(ArrayList<ArrayList<NodeWrapper>> wrapperArray, NodeWrapper currentNode, int level, NodeWrapper parent) {
        System.out.println("Node [" + currentNode.node.getData().toString() + "] has left child: " + (currentNode.node.left != null));
        if (currentNode.node.left != null) {
            NodeWrapper leftChild = new NodeWrapper(currentNode.node.left, level + 1);
            leftChild.parent = currentNode;
            wrapperArray.get(level).add(leftChild);
            wrapNodes(wrapperArray, leftChild, level + 1, currentNode);
        }

        // Wrap current node if root

        // If wrapping root.
        /*if ((currentNode.level == 0)  && (!(currentNode.parent == currentNode))) { 
            NodeWrapper rootNode = new NodeWrapper(currentNode.node, level);
            wrapperArray.get(0).add(rootNode);
        }*/
        System.out.println("Node [" + currentNode.node.getData().toString() + "] has right child: " + (currentNode.node.right != null));

        if (currentNode.node.right != null) { 
            NodeWrapper rightChild = new NodeWrapper(currentNode.node.right, level + 1);
            rightChild.parent = currentNode;
            wrapperArray.get(level).add(rightChild);
            wrapNodes(wrapperArray, rightChild, level + 1, currentNode);
        }
    }

    public void printImage() { 
        for (int i = 0; i < treeImage.size(); ++i) { 
            for (int j = 0; j < treeImage.get(i).size(); ++j)  { 
                
                String parentData = "";
                
                if (i > 0) {
                    parentData = treeImage.get(i).get(j).parent.node.getData().toString();
                }
                System.out.println(treeImage.get(i).get(j).node.getData().toString() + " level " + treeImage.get(i).get(j).level + " parent " + parentData);
            }
            System.out.println();
        }
    }

    //@Override
    public BufferedImage renderDiagram(BinarySearchTree<T> tree, String title) {
        int imageWidth = 1;
        int imageHeight = 1;

        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics;

        int treeHeight = tree.getHeight();

        imageHeight = levelHeight * treeHeight + (2 * vBuffer) + 200;

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


        // Fill with white background
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        // Write title
        graphics.setColor(Color.BLACK);
        graphics.drawString(title, titleX, titleY);

        int elemX, elemY, xIncrement, nodesOnCurrLevel;

        // Work through the image, writing nodes.
        for (int i = 0; i < treeImage.size(); ++i) { 
            
            elemY = ((levelHeight) * 2 * (i + 1)) + 60;
            System.out.println("ElemY = " + elemY);
            nodesOnCurrLevel = treeImage.get(i).size();
            
            for (int j = 0; j < nodesOnCurrLevel; ++j) { 
                NodeWrapper currWrapper = treeImage.get(i).get(j);
                nodeWidth = fontMetrics.stringWidth(currWrapper.node.getData().toString()) * 2 + 5;

                // TODO: Improve the way the element's x-coordinate is determined

                xIncrement = (imageWidth / (nodesOnCurrLevel));

                elemX = xIncrement * (j) + (hBuffer / 2);
                
                if (currWrapper.parent != null) {
                    if (currWrapper.parent.node.left == currWrapper.node) { 
                        elemX -= nodeWidth;
                    } else { 
                        elemX += nodeWidth;
                    }
                }
                
                elemX += (xIncrement / 2);
                
                if (nodesOnCurrLevel == 1) { 
                    elemX = imageWidth / 2;
                }


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
        }

        return bufferedImage;
    }

    public void saveFile(BinarySearchTree<T> tree, String title, String filepath) { 
        BufferedImageFileWriter.writeToFile(this.renderDiagram(tree, title), filepath);
    }

}
