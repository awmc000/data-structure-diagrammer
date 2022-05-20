package com.datastructurediagrammer.linkedlists;

import java.awt.image.BufferedImage;

import com.datastructurediagrammer.util.BufferedImageFileWriter;
import com.datastructurediagrammer.util.DataStructureDiagrammer;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * @author Alex McColm
 */
public class DoublyLinkedListDiagrammer <T extends Comparable<T>> 
extends SinglyLinkedListDiagrammer 
implements DataStructureDiagrammer<DoublyLinkedList<T>> {

    /**
     * 
     * @param graphics
     * @param imageWidth
     * @param i
     * @param nodeHeight
     * @param margin
     */
    private void drawPreviousPointer(Graphics2D graphics,
            int imageWidth, int i, int nodeHeight, int margin) {

        graphics.setColor(Color.BLUE);

        int x1, x2, y1, y2;

        x1 = (imageWidth / 4) * 3;
        x2 = x1;

        y1 = ((i - 1) * nodeHeight + margin) + graphics.getFontMetrics().getHeight();
        y2 = y1 + 13;

        graphics.drawLine(x1, y1, x2, y2);

        // Puts the arrow on the other vertical end when drawing prev ptr
        int[] arrowXPoints = { x1 - 2, x1, x1 + 2 };
        int[] arrowYPoints = { y1 + 2, y1, y1 + 2 };
        graphics.drawPolygon(arrowXPoints, arrowYPoints, 3);

    }

    /**
     * 
     * @param list The doubly linked list object for which to draw a diagram
     * @param title The title for the diagram, written at top centre.
     * @return A BufferedImage object of a completed DLL diagram.
     */
    public BufferedImage renderDiagram(DoublyLinkedList<T> list, String title) {
        final int imageWidth = 90;
        final int nodeHeight = 30;
        int imageHeight = list.getLength() * nodeHeight;

        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        // Graphics object could be thought of as our pen.
        // the BufferedImage has a getGraphics() method returning a Graphics object, we
        // cast it to be a Graphics2D object.
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();

        // Set color to white, and fill the image background.
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        //TODO: print title nicely
        graphics.drawString(title, 5, 5);

        // Margin is used between different things. eg. there is a margin
        // between the text and the top and bottom of the rectangle
        int margin = 2;

        // Width of a digit - varies wildly, but we will use a wider digit for this
        // purpose.
        int digitWidth = graphics.getFontMetrics().charWidth('0');

        int stringWidth;

        DLLNode<T> currentNode = list.head;

        for (int i = 0; i < list.getLength(); ++i) {
            graphics.setColor(Color.black);

            String dataString = currentNode.getData().toString();// listArr.get(i).toString();

            stringWidth = dataString.length();

            graphics.drawRect(imageWidth / 5, i * nodeHeight + margin, (imageWidth / 5) * 3,
                    graphics.getFontMetrics().getHeight() + 2 * margin);
            graphics.drawString(dataString, imageWidth / 2 - stringWidth * digitWidth / 2,
                    (nodeHeight * i) + graphics.getFontMetrics().getHeight());

            if (currentNode.next != null) {
                drawNextPointer(graphics, imageWidth, i, nodeHeight, margin);
            }
            
            if (currentNode.previous != null) {
                drawPreviousPointer(graphics, imageWidth, i, nodeHeight, margin);
            }
            // Continue traversal, to next node which needs to be drawn.
            currentNode = currentNode.next;
        }

        // Close graphics object, similar to closing a scanner
        graphics.dispose();

        return bufferedImage;
        
    }

    /**
     * Shortcut method that calls to render the diagram to buffered image 
     * and passes it to the BufferedImageFileWriter in order to write to file.
     * @param bufferedImage
     * @param filepath
     */
    public void renderDiagramFile(BufferedImage bufferedImage, String filepath) { 
        // Construct the file object
        BufferedImageFileWriter.writeToFile(bufferedImage, filepath);
    }

}
