package com.datastructurediagrammer.sorting;

public class BubbleSortProblemGenerator <T extends Comparable<T>> {
    private BubbleSortArrayDiagrammer<T> diagrammer;

    public BubbleSortProblemGenerator(T[] array) { 
        this.diagrammer = new BubbleSortArrayDiagrammer<T>();
    }

    //@Override
    public String generateWorksheet(String title) {
        String workSheet = "";
        workSheet += title + "\n" + getRepeated("=", title.length());
        return null;
    }

    private String getRepeated(String toRepeat, int num) { 
        String str = "";
        for (int i = 0; i < num; i++) { 
            str += toRepeat;
        }
        return str;
    }
     
}
