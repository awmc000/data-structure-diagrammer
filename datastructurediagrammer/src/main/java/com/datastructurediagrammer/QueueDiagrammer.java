package com.datastructurediagrammer;

import java.awt.image.BufferedImage;

import com.datastructurediagrammer.linkedlists.DoublyLinkedList;
import com.datastructurediagrammer.linkedlists.DoublyLinkedListDiagrammer;
import com.datastructurediagrammer.linkedlists.Queue;

import java.awt.Graphics2D;
import java.awt.Color;

public class QueueDiagrammer<T extends Comparable<T>> implements DataStructureDiagrammer<Queue<T>>  {
    private DoublyLinkedListDiagrammer<T> dllDiagrammer;
    
    public QueueDiagrammer() { 
        this.dllDiagrammer = new DoublyLinkedListDiagrammer<>();
    }

    @Override
    public BufferedImage renderDiagram(Queue<T> queue, String title) {
        BufferedImage diagram = dllDiagrammer.renderDiagram(queue.getList(), title);

        Graphics2D graphics = (Graphics2D) diagram.getGraphics();

        return null;
    }
}
