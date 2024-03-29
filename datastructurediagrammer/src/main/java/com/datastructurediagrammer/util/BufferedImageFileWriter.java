package com.datastructurediagrammer.util;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

/*
 * BufferedImageFileWriter
 * 
 * Purpose: Take buffered images, turn them to image files,
 * and write them to the file system.
 * 
 * Input/Output: See "writeToFile()" method.
 */

public class BufferedImageFileWriter {
    /**
     * 
     * @param bufferedImage the completed diagram to write to file
     * @param filepath the path to which to save the diagram
     * @return returns the file which can likely be discarded
     */
    public static File writeToFile(BufferedImage bufferedImage, String filepath) { 
        if (!(filepath.endsWith(".png"))) { 
            filepath += ".png";
        }
        
        // Construct the file at filepath given as param
        File finishedFile = new File(filepath);

        // The big moment: Write the finished result to a PNG file
        try { 
            ImageIO.write(bufferedImage, "PNG", finishedFile);
            // Tell the user of the CLI that the image is saved and where it is.
            System.out.println("Saved image sucessfully at " + filepath);
        } 
        catch (IOException e) { 
            System.out.println("Had a problem writing BufferedImage to file!");
            e.printStackTrace();
        }
        
        return finishedFile;
} 
}
