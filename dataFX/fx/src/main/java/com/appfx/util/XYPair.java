package com.appfx.util;

public class XYPair {
    public final int x;
    public final int y;
    
    public XYPair(int x, int y) { 
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object otherObject) { 
        if (!(otherObject instanceof XYPair)) { 
            return false;
        }
        XYPair otherXYPair = (XYPair) otherObject;
        return ((this.x == otherXYPair.x) && (this.y == otherXYPair.y));
    }
}