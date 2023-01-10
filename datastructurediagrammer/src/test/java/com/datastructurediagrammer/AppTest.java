package com.datastructurediagrammer;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testMain() {
        String[] args = {"array", "int", "1", "2", "3"};
        App.main(args);
    }

    @Test
    public void testMakeArray() {
        String[] args = {"array", "string", "hello", "world", "goodbye", "mars"};
        App.makeArray(args, "String", args.length - 2);
    }
}
