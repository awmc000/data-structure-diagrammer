package com.datastructurediagrammer.linkedlists;

import java.awt.image.BufferedImage;
import com.datastructurediagrammer.util.BufferedImageFileWriter;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.FontMetrics;

public class SinglyLinkedListDiagrammer /*extends DataStructureDiagrammer<SinglyLinkedList>*/ {
    /* Where is currentNode used in this method? **removed**
     * If this method does not take a SLLNode as parameter, it can be used exactly as is by DoublyLinkedListDiagrammer,
     * so I can have DoublyLinkedListDiagrammer extend this class.
     */
    protected static <T extends Comparable<T>> void drawNextPointer(Graphics2D graphics, 
            int imageWidth, int i, int nodeHeight, int margin) {
        // (list.getLength() >= i + 2) {

        // The line representing the next pointer will be red
        graphics.setColor(Color.red);

        // declare integers for the 4 coordinates of the line
        int x1, x2, y1, y2;

        // The line representing the next pointer should be toward the left side
        x1 = imageWidth / 4;
        x2 = x1;

        int fontHeight = graphics.getFontMetrics().getHeight();

        y1 = (i * nodeHeight + margin) + fontHeight;
        y2 = y1 + 13;
        // int rectHeight = (i * nodeHeight + margin) +
        // graphics.getFontMetrics().getHeight() + 2 * margin;
        // y2 = rectHeight;

        graphics.drawLine(x1, y1, x2, y2);

        // Finally, draw a triangle to be the point of the arrow.
        int[] arrowXPoints = { x1 - 2, x1, x1 + 2 };
        int[] arrowYPoints = { y2 - 2, y2, y2 - 2 };
        graphics.drawPolygon(arrowXPoints, arrowYPoints, 3);
    }
    
    public static <T extends Comparable<T>> void/*File*/ renderDiagram(SinglyLinkedList<T> list, String title, String filepath) { 
        final int imageWidth = 90;
        final int nodeHeight = 30;
        //int hBuffer = 30;
        int vBuffer = 30;
        int imageHeight = list.getLength() * nodeHeight + (2 * vBuffer); // For 4 nodes, img will be 120px, etc
        
        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        // Graphics object could be thought of as our pen.
        // the BufferedImage has a getGraphics() method returning a Graphics object, we cast it to be a Graphics2D object.
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        
        // Set color to white, and fill the image background.
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        // Margin is used between different things. eg. there is a margin 
        // between the text and the top and bottom of the rectangle
        int margin = 2;

        // Width of a digit - varies wildly, but we will use a wider digit for this purpose.
        int digitWidth = graphics.getFontMetrics().charWidth('0');

        int stringWidth;

        SLLNode<T> currentNode = list.head;

        FontMetrics fontMetrics = graphics.getFontMetrics();

        int fontHeight = fontMetrics.getHeight();

        for (int i = 0; i < list.getLength(); ++i) { 
            graphics.setColor(Color.black);
            
            String dataString = currentNode.getData().toString();//listArr.get(i).toString();

            stringWidth = dataString.length();

            graphics.drawRect(imageWidth / 5, i * nodeHeight + margin, (imageWidth / 5) * 3, fontHeight + 2 * margin);
            graphics.drawString(dataString, imageWidth / 2 - stringWidth * digitWidth / 2, (nodeHeight * i) + fontHeight);
            //graphics.drawRect(imageWidth / 2 - stringWidth * digitWidth / 2, nodeHeight * i - margin, imageWidth / 2, graphics.getFontMetrics().getHeight() + 2 * margin);
            
            // If there is a next node, draw a red vertical line representing the next pointer.
            if (currentNode.next != null) {
                drawNextPointer(graphics, imageWidth, i, nodeHeight, margin);
            }

            // Continue traversal, to next node which needs to be drawn.
            currentNode = currentNode.next;
        }

        // Close graphics object, similar to closing a scanner
        graphics.dispose();

        // Construct the file object
        BufferedImageFileWriter.writeToFile(bufferedImage, filepath);
    }
}
