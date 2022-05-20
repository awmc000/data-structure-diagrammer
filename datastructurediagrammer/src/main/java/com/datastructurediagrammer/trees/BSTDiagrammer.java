package com.datastructurediagrammer.trees;

import java.awt.image.BufferedImage;

import com.datastructurediagrammer.util.BufferedImageFileWriter;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Use BSTDiagrammer3 instead.
 * @deprecated BSTDiagrammer3
 */
public class BSTDiagrammer {
    public static <T extends Comparable<T>> void draw(Graphics2D graphics, BSTNode<T> node, int x, int y, int increment, int level, int treeHeight) { 
        //increment *= 2;
        int currXIncrement = (increment * (treeHeight - level));
        int halfIncrement = increment / 2;
        if (node == null) { 
            return;
        } else {
            if ((node.left != null)) {
                if ((node.left.left != null) ^ (node.left.right != null)) { 
                    draw(graphics, node.left, x - increment - (currXIncrement / 2), y + (2 * increment), increment, level + 1, treeHeight);
                    graphics.drawLine(x, y + halfIncrement, x - halfIncrement - (currXIncrement / 2), y + (2 * increment));
                } 
                // If right child is an internal node with 1 child, draw right child's children at half distance.
                else if ((node.left.left != null) || (node.left.right != null)) {
                    // If left node is an internal node:
                    draw(graphics, node.left, x - increment - currXIncrement, y + (2 * increment), increment, level + 1, treeHeight);
                    graphics.drawLine(x, y + halfIncrement, x - halfIncrement - currXIncrement, y + (2 * increment));
                }
                else {
                    // If left node is a leaf:
                    draw(graphics, node.left, x - increment, y + (2 * increment), increment, level + 1, treeHeight);
                    graphics.drawLine(x, y + halfIncrement, x - halfIncrement, y + (2 * increment));
                }
            }
           
            // draw current bst node
            graphics.setColor(Color.BLACK);
            graphics.drawOval(x, y, increment, increment);
            //graphics.drawRect(x, y, 2, 2);
            String dataString = node.getData().toString();
            graphics.drawString(dataString, x + halfIncrement - (graphics.getFontMetrics().stringWidth(dataString) / 2), y + halfIncrement);;
            
            // If node has a right child
            if (node.right != null) { 
                // If right child is an internal node with 1 child, draw it at half distance
                if ((node.right.left != null) ^ (node.right.right != null)) { 
                    draw(graphics, node.right, x + increment + (currXIncrement / 2), y + (2 * increment), increment, level + 1, treeHeight);
                    graphics.drawLine(x + increment, y + halfIncrement, x + increment + halfIncrement + (currXIncrement / 2), y + (2 * increment));
                }
                // If right child is an internal node with 1 or 2 children, draw right child's children further apart.
                else if ((node.right.left != null) || (node.right.right != null)) { 
                    draw(graphics, node.right, x + increment + currXIncrement, y + (2 * increment), increment, level + 1, treeHeight);
                    graphics.drawLine(x + increment, y + halfIncrement, x + increment + halfIncrement + currXIncrement, y + (2 * increment));
                } 
                // If right child is a leaf node, draw right child's children closer. 
                else {
                    draw(graphics, node.right, x + increment, y + (2 * increment), increment, level + 1, treeHeight);
                    graphics.drawLine(x + increment, y + halfIncrement, x + increment + halfIncrement, y + (2 * increment));
                }
            }
        }
    }

    public static <T extends Comparable<T>> void renderDiagram(BinarySearchTree<T> bst, String filepath) { 
        int nodeSquare = 40;
        int imageHeight = nodeSquare * bst.getHeight() * 3;

        int imageWidth = nodeSquare * bst.getHeight() * bst.getHeight(); 

        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        // recursive draw method called on root
        draw(graphics, bst.root, imageWidth / 2, 0, nodeSquare, 0, bst.getHeight());

        // close graphics object
        graphics.dispose();

        BufferedImageFileWriter.writeToFile(bufferedImage, filepath);
    }
}
