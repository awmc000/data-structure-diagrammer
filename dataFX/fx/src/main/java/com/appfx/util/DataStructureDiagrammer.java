package com.appfx.util;

import java.awt.image.BufferedImage;

/**
 * An interface to be implement by all data structure diagrammers. 
 */
public interface DataStructureDiagrammer<DataStructure> {
    /**
     * This method takes
     * a data structure and a title and draws a titled diagram to
     * a BufferedImage.
     * 
     * @param ds the data structure to be diagrammed
     * @param title the title to be drawn on the diagram
     * @return BufferedImage object containing a completed diagram
     */
    public abstract BufferedImage renderDiagram(DataStructure ds, String title);
}
