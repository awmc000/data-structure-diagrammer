package com.datastructurediagrammer.trees;

//import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
//import java.awt.FontMetrics;
//import java.util.ArrayList;

/*
 * AVLTreeDiagrammer
 * 
 * Purpose: Generate a diagram of an AVL tree, extending
 * the capability of the BST diagrammer by adding height and balance
 * factor information. Result should be a BufferedImage.
 */
public class AVLTreeDiagrammer<T extends Comparable<T>> extends BSTDiagrammer<T> {

    public AVLTreeDiagrammer(BinarySearchTree<T> tree) {
        super(tree);
        //TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    @Override 
    protected void drawNode(Graphics2D graphics, NodeWrapper currWrapper, int elemX, int elemY) {
        //super(graphics, currWrapper, elemX, elemY); //TODO: why doesn't this work?
        // Write height and balance factor
        int stringWidth = graphics.getFontMetrics().stringWidth(currWrapper.node.data.toString());
        int balanceFactorX = elemX + stringWidth + 10;
        int balanceFactorY = elemY;
        int bal = ((AVLNode<T>) currWrapper.node).getBalance();
        if (bal < 0) { 
            graphics.setColor(Color.RED);
        } else if (bal == 0) { 
            graphics.setColor(Color.GREEN);
        } else if (bal > 0) { 
            graphics.setColor(Color.BLUE);
        }
        graphics.drawString(Integer.toString(bal), balanceFactorX, balanceFactorY);
    }
    
}
