package com.datastructurediagrammer.sorting;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Color;

import com.datastructurediagrammer.BufferedImageFileWriter;
import com.datastructurediagrammer.arrays.ArrayDiagrammer;

/**
 * Generates diagrams of each step as it performs a bubble sort on the given array.
 * @author Alex McColm
 */
public class BubbleSortArrayDiagrammer <T extends Comparable<T>> {
    /**
     * The BubbleSortDiagrammer owns an ArrayDiagrammer which it 
     * calls at each step.
     */
    private ArrayDiagrammer<T> arrayDiagrammer;

    public BubbleSortArrayDiagrammer() { 
        arrayDiagrammer = new ArrayDiagrammer<T>();
    }

    /**
     * 
     * @param array Array to be sorted and have its sorting process diagrammed.
     * @param title Title to be written to the top centre of the diagram.
     * @param dirpath  Absolute path of the system directory in which to create a folder of images of stages of bubblesort
     */
    public void renderSortingOperation(T[] array, String title, String dirpath) { 
        int swapNum = 1;

        // This timestamp will be used as part of the folder name
        String timeStamp = LocalDateTime.now().toString().replace(':', '-');

        String dirName = dirpath + "/" + timeStamp + " Bubble Sort of " + title + "/";

        // Make the folder
        try {
            Files.createDirectories(Paths.get(dirName));
        } catch (IOException e) { 
            System.out.println("Problem creating directory " + dirName + "!");
            e.printStackTrace();
        }

        String firstFilename = dirName  + "Before Bubble Sort of " + title + ".png";
        arrayDiagrammer.drawToFile(array, title + " Before Bubble Sort", firstFilename);

        /* These arraylists should match up side by side such that a bufferedImage 
         * and its fileName have the same index in the two ArrayLists. That is, 
         * the filename for  bufferedImages.get(0) should be at fileNames.get(0)
         */
        ArrayList<BufferedImage> bufferedImages = new ArrayList<>();
        ArrayList<String> fileNames = new ArrayList<String>();


        /* Each step of the bubble sorting operation,
         * a new diagram will be drawn; its BufferedImage
         * representation will be assigned to bufferedImage, 
         * and its Graphics2D object to graphics.
         * 
         * Using this, we can write on the diagram what the
         * variables i and j currently are and what swap
         * is being made, etc.
         */
        Graphics2D graphics;
        BufferedImage bufferedImage;

        for (int i = 0; i < array.length - 1; ++i) { 
            for (int j = 0; j < array.length - i - 1; ++j) {
                bufferedImage = arrayDiagrammer.renderDiagram(array, title);
                graphics = (Graphics2D) bufferedImage.getGraphics();

                if (array[j].compareTo(array[j + 1]) > 0) {
                    
                    // Perform the swap itself.
                    String swapString = "SWAP " + swapNum + ": " + array[j] + " & " + array[j + 1];
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    
                    // If there was a swap made, let's write that to the top left.
                    graphics.setColor(Color.BLUE);
                    graphics.drawString(swapString, 5, 20);
                    graphics.setColor(Color.BLACK);

                    swapNum++;
                }

                graphics.setColor(Color.BLACK);
                graphics.drawString("i = " + i + "\nj = " + j, 5, 10);
                
                bufferedImages.add(bufferedImage);
                fileNames.add(dirName  + timeStamp +  "Bubble Sort @ i = " + i + ", j = " + j + " of " + title + ".png");
            }
        }
        

        // Turn all the buffered images to files.
        for (int i = 0; i < bufferedImages.size(); ++i) { 
            BufferedImageFileWriter.writeToFile(bufferedImages.get(i), fileNames.get(i));
        }
    }
}
