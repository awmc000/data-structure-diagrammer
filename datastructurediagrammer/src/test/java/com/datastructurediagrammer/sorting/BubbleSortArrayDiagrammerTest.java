package com.datastructurediagrammer.sorting;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BubbleSortArrayDiagrammerTest {
    @Test
    public void filesGeneratedTest() { 
        BubbleSortArrayDiagrammer<Integer> diagrammer = new BubbleSortArrayDiagrammer<>();
        Integer[] nums = {2, 4, 812, 23, 54, 3, 66, 88, 92, 1230, -4};
        System.out.println("Wrote diagrams to: " + System.getProperty("user.dir"));
        ArrayList<String> fileNames = diagrammer.renderSortingOperation(nums, 
        "Numbers Diagram", System.getProperty("user.dir"));
        assertTrue(fileNames.size() > 0);
    }
}
