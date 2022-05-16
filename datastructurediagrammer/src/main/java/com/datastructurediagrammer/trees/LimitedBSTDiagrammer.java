package com.datastructurediagrammer.trees;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.FontMetrics;
import java.io.File;

import com.datastructurediagrammer.BufferedImageFileWriter;
import com.datastructurediagrammer.XYPair;

import java.util.ArrayList;

public class LimitedBSTDiagrammer<T extends Comparable<T>> {
    // Oh what could have been. so much more efficient.
    //public record nodeWrapper(BSTNode<T> node, BSTNode<T> parent, int level) {}
    class NodeWrapper { 
        /* These fields are final, they should not be changed after construction whatsoever.
         * They are public though so they can be READ (not written to) easily.
         * 
         * So, if we want to show operations on a BST, we can manipulate the BST itself just fine.
         * But we need to re-wrap (put them in wrappers again) and draw new diagrams each step. 
         */
        public final BSTNode<T> node; // The node which is wrapped.
        public final BSTNode<T> parent; // Parent pointer for drawing a line to it
        public final int level; // Level in the BST, root is 0
        public final int hOffset; // horizontal offset
        public final boolean isLeftChild; // If true, this is left child. If false, this is a right child or root.

        public NodeWrapper(BSTNode<T> node, BSTNode<T> parent, int level, int hOffset) { 
            this.node = node;
            this.parent = parent;
            this.level = level;
            this.hOffset = hOffset;
            if ((this.parent != null) && (node == parent.left)) { 
                isLeftChild = true;
            } else { 
                isLeftChild = false;
            }
        }
    }



    // Recursive method following the general pattern of an in-order traversal.
    private void wrapNodes(ArrayList<NodeWrapper> wrapperArray, BinarySearchTree<T> tree, BSTNode<T> node, BSTNode<T> parent, int level, int hOffset) { 
        if (node.left != null) { 
            wrapNodes(wrapperArray, tree, node.left, node, level + 1, hOffset + 1);
            wrapperArray.add(new NodeWrapper(node.left, node, level + 1, hOffset + 1));
        }
        /* The node at level 0, root, is the only node which is added while at that level. 
         * All descendant nodes are added from the level above, by the checks 
         * for children. 
         */
        if (node == tree.root) { 
            wrapperArray.add(new NodeWrapper(node, null, 0, 0));
        }
        if (node.right != null) { 
            wrapNodes(wrapperArray, tree, node.right, node, level + 1, hOffset - 1);
            wrapperArray.add(new NodeWrapper(node.right, node, level + 1, hOffset - 1));
        }
    }

    public void wrapNodes(ArrayList<NodeWrapper> wrapperArray, BinarySearchTree<T> tree) { 
        wrapNodes(wrapperArray, tree, tree.root, null, 0, 0);
    }

    public File renderDiagram(BinarySearchTree<T> tree, int hBuffer, int vBuffer, String title, String filepath) {
        // Tree nodes will be stored as wrappers
        ArrayList<NodeWrapper> wrapperArray = new ArrayList<>();

        // Coordinates which have been printed to will be stored here as a rough way to prevent overlaps
        ArrayList<XYPair> coordinatesUsed = new ArrayList<>();

        wrapNodes(wrapperArray, tree);

        // Find the widest level:
        int[] levelPopulations = new int[tree.getHeight()];
        int widestLevel = 0;
        
        // Count how many nodes on each level
        for (int i = 0; i < wrapperArray.size(); ++i) { 
            levelPopulations[wrapperArray.get(i).level] = wrapperArray.get(i).level;
        }

        // Find the level with the most nodes and assign it to widestLevel
        for (int i = 0; i < levelPopulations.length; ++i) {
            if (levelPopulations[i] > widestLevel) { 
                widestLevel = levelPopulations[i];
            }
        } 

        // PLACEHOLDER
        // TODO: determine actual width of widestElement.toString(),
        int widestElement = 40;

        // Determine width and height of individual cells
        int cellWidth = widestElement * 2;
        int cellHeight = 20; // PLACEHOLDER

        // Determine width and height of image based on number of objects and multipliers given.
        int imageWidth = (3 * hBuffer) + (widestLevel * 2) * (cellWidth * 2); // TODO: Calculate this better
        int imageHeight = (2 * vBuffer) + (tree.getHeight() * cellHeight * 2);
        // Set up BufferedImage of a size based on the height and max width
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        // Get the graphics object from it
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        
        // Fill white background
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        // Grab font metrics
        FontMetrics fontMetrics = graphics.getFontMetrics();

        // Font height
        int fontHeight = fontMetrics.getHeight();

        // Set pen to black
        graphics.setColor(Color.BLACK);

        // Draw title in the centre of the vertical buffer above the BST.
        int titleX = (imageWidth / 2) - (fontMetrics.stringWidth(title) / 2);
        int titleY = vBuffer / 2;
        
        graphics.drawString(title, titleX, titleY);

        System.out.println("The wrapper array has size" + wrapperArray.size());

        
        for (int i = 0; i < wrapperArray.size(); ++i) { 
            // Current node. Brand new, still in the wrapper :) 
            NodeWrapper currentNodeWrapper = wrapperArray.get(i);
            

            if (!(currentNodeWrapper.node != null)) { 
                continue;
            }

            // String form of current node's data 
            String currentData = currentNodeWrapper.node.getData().toString();
            System.out.println("handling wrapper of node " + currentData + " current level " 
            + currentNodeWrapper.level + " current hOffset " + currentNodeWrapper.hOffset);
            
            // x-coordinate to write current data
            int dataX = (imageWidth / 2) - 2 * (currentNodeWrapper.hOffset * cellWidth) - hBuffer; // TODO: Calculate better
            
            // y-coordinate to write current data
            int dataY = vBuffer + (2 * cellHeight * currentNodeWrapper.level);

            XYPair currentLocation = new XYPair(dataX, dataY);
            
            if (coordinatesUsed.contains(currentLocation)) { 
                System.out.println("Skipped node: Not drawing " + currentNodeWrapper.node.getData().toString() + " because it would cause overlap");
                continue;
                //throw OverlappingDiagramException;
            }

            coordinatesUsed.add(currentLocation);


            
            // draw rectangle around data
            graphics.drawRect(dataX, dataY, cellWidth, cellHeight);

            // curr data item width in pixels when printed
            int currStringWidth = fontMetrics.stringWidth(currentData);

            // centred position 
            int centre = ((cellWidth - currStringWidth) / 2);

            // write the data value itself
            graphics.drawString(currentData, dataX + centre, dataY + fontHeight);

            
            // If left child not null, draw a line to it
            if (currentNodeWrapper.node.left != null) { 
                int x1, y1, x2, y2;
                x1 = dataX + (cellWidth / 2); // centre of current box
                y1 = dataY + cellHeight; // bottom of current box

                x2 = x1 - (cellWidth * 2);
                y2 = y1 + cellHeight;
                graphics.drawLine(x1,y1,x2,y2);
            }
            if (currentNodeWrapper.node.right != null) { 
                int x1, y1, x2, y2;
                x1 = dataX + (cellWidth / 2); // centre of current box
                y1 = dataY + cellHeight; // bottom of current box

                x2 = x1 + (cellWidth * 2);
                y2 = y1 + cellHeight;
                graphics.drawLine(x1,y1,x2,y2);
            }
        }
        
        return BufferedImageFileWriter.writeToFile(bufferedImage, filepath);
    }
}
