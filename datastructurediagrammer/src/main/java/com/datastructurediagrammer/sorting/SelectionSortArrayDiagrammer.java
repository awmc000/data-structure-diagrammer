package com.datastructurediagrammer.sorting;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.datastructurediagrammer.arrays.ArrayDiagrammer;
import com.datastructurediagrammer.util.TimeStamp;

public class SelectionSortArrayDiagrammer<T extends Comparable<T>> implements SortDiagrammer<T> {

    private ArrayDiagrammer<T> arrayDiagrammer;

    public boolean writesSteps;

    public SelectionSortArrayDiagrammer() {
        arrayDiagrammer = new ArrayDiagrammer<T>();
        writesSteps = true;    
    }

    @Override
    public ArrayList<String> renderSortingOperation(T[] array, String title, String dirpath) {
        int swapNum = 1;
                // TODO Auto-generated method stub
        // This timestamp will be used as part of the folder name
        String timeStamp = TimeStamp.ts();

        String dirName = dirpath + "/" + timeStamp + " Bubble Sort of " + title + "/";

        setupDirs(dirName);

        String firstFilename = dirName + "Before Bubble Sort of " + title + ".png";
        arrayDiagrammer.drawToFile(array, title + " Before Bubble Sort", firstFilename);

        /*
         * These arraylists should match up side by side such that a bufferedImage
         * and its fileName have the same index in the two ArrayLists. That is,
         * the filename for bufferedImages.get(0) should be at fileNames.get(0)
         */
        ArrayList<BufferedImage> bufferedImages = new ArrayList<>();
        ArrayList<String> fileNames = new ArrayList<String>();

        // i is the next item in the unsorted partition.
        // items from 0 up to & excluding i are sorted.
        
        //
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[min]) < 0)
                    min = i;
            }
            if (min != i) {
                // Swap array[i] and array[min]
                // A: array[i]
                // B: array[min]
                // C: temp
                T temp;
                temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
        return fileNames;
    }

    @Override
    public void makeFiles(ArrayList<BufferedImage> bufferedImages, ArrayList<String> fileNames) {
    
    }

    @Override
    public void setupDirs(String dirName) {
        // Make the folder
        try {
            Files.createDirectories(Paths.get(dirName));
        } catch (IOException e) {
            System.out.println("Problem creating directory " + dirName + "!");
            e.printStackTrace();
        }
    }
    
}
