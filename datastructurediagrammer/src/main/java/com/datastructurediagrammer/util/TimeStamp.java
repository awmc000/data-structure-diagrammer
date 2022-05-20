package com.datastructurediagrammer.util;

import java.time.LocalDateTime;

public class TimeStamp {
    public static String ts() { 
        return LocalDateTime.now().toString().replace(':', '-');
    } 
}
