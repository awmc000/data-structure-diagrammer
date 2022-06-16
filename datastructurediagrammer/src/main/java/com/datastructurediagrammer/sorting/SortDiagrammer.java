package com.datastructurediagrammer.sorting;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public interface SortDiagrammer<T extends Comparable<T>> {
    /**
     * Renders .png images and returns an ArrayList of filenames of the images.
     * @param array
     * @param title
     * @param dirpath
     * @return ArrayList of filenames of images.
     */
    public ArrayList<String> renderSortingOperation(T[] array, String title, String dirpath);

    /**
     * Makes files of the diagrams of each step of the sort.
     * @param bufferedImages
     * @param fileNames
     */
    public void makeFiles(ArrayList<BufferedImage> bufferedImages, ArrayList<String> fileNames);
}
