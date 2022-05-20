package com.datastructurediagrammer.arrays;

import java.awt.image.BufferedImage;

import com.datastructurediagrammer.util.BufferedImageFileWriter;
import com.datastructurediagrammer.util.DataStructureDiagrammer;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Color;

public class ArrayDiagrammer<T> implements DataStructureDiagrammer<T[]> {
    /**
     * Returns a BufferedImage object of a completed diagram of an array.
     * 
     * @param array an array to be diagrammed
     * @param title the string given as title will be printed at top centre
     * @return buffered image containing diagram of given array with given title
     */
    public BufferedImage renderDiagram(T[] array, String title) { 
        int imageWidth = 1;
        int imageHeight = 1;

        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);;
        
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();


        // Find the element which will make the widest element.
        int maxElemWidth = 0;
        
        FontMetrics fontMetrics = graphics.getFontMetrics();

        String currString;
        int currWidth;

        for (int i = 0; i < array.length; ++i) { 
            currString = array[i].toString();
            currWidth = fontMetrics.stringWidth(currString);

            if (currWidth > maxElemWidth) { 
                maxElemWidth = currWidth;
            }
        }

        // Calculate what the width should be based on the new knowledge.
        int hBuffer = 20; // Empty space on left/right sides of the array 左边和右边
        int vBuffer = 50; // Empty space on top/down sides of the array 下面和上面
        int cellHeight = fontMetrics.getHeight() * 2; // Height of an individual cell
        int cellWidth = maxElemWidth * 2;

        imageWidth = (cellWidth * array.length) + (2 * hBuffer);
        imageHeight = (2 * vBuffer) + cellHeight; 

        // Resize the BufferedImage real quick
        bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) bufferedImage.getGraphics();
        
        // Fill the img with white background
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        // Set current drawing color to black
        graphics.setColor(Color.BLACK);

        // Draw the title of the array - if null this should do nothing
        int titleWidth = fontMetrics.stringWidth(title);
        int titleHeight = fontMetrics.getHeight();
        graphics.drawString(title, (imageWidth / 2) - (titleWidth / 2), vBuffer - titleHeight);

        // Formula for an individual cell's x-position: hBuffer + (i * cellWidth)
        // This is the position of the top left corner of the rectangle outlining that cell
        for (int i = 0; i < array.length; ++i) { 
            int cellX = hBuffer + (i * cellWidth);
            int cellY = vBuffer;
            int elemWidth = fontMetrics.stringWidth(array[i].toString());
            graphics.drawRect(cellX, cellY, cellWidth, cellHeight);
            graphics.drawString(array[i].toString(), cellX + (cellWidth / 2) - (elemWidth / 2), cellY + (cellHeight / 2));
        }

        return bufferedImage;
    }

    /**
     *  Shortcut method that renders and saves given array with given title to given filepath.
     * @param array the array to diagram and then write to file
     * @param title the title for the array
     * @param filepath the path to write the file to. A check for .png is included so a string ending with or without the ".png" is fine.
     */
    public void drawToFile(T[] array, String title, String filepath) { 
        BufferedImageFileWriter.writeToFile(renderDiagram(array, title), filepath);
    }
}
