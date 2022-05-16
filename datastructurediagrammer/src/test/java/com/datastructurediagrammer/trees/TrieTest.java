package com.datastructurediagrammer.trees;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TrieTest {
    public Trie testTrie; // Let's set aside some memory for the instance of this which each method will assign to it.

    /**
     * Tests if overlapping words can be inserted and checked for properly.
     */
    @Test
    public void overlappingWords() {
        testTrie = new Trie();
        String[] overlappingWords = {"I", "IN", "INN", "INTO", "INPUT", "INK", 
        "INNKEEPER", "INTUITION", "INTUITIVE", "INTUITIVELY", "INSULATION", "INTERIOR"};

        // Add all the overlapping words.
        testTrie.add(overlappingWords);

        // Test that they are contained.
        assertTrue(testTrie.contains(overlappingWords));
    }

    /**
     * Tests if words with mixed case present any issue.
     */
    @Test 
    public void mixedCases() { 
        testTrie = new Trie();
        String[] mixedCaseWords = {"differ", "Differ", "DIffer", "DIFfer", "DIFFer", "DIFFEr", "DIFFER"};

        testTrie.add(mixedCaseWords);

        assertTrue(testTrie.contains(mixedCaseWords));
    }

    /**
     * Tests if spaces are handled properly.
     */
    @Test 
    public void spaces() { 
        testTrie = new Trie();
        String[] spacedWords = {"ONE FISH", "TWO FISH", "RED FISH", "BLUE FISH",
                                "ONE FISH IS SICK", "TWO FISH ARE RED", "RED FISH ARE WINNERS",
                                "BLUE FISH FOR DINNER"};

        assertTrue(testTrie.contains(spacedWords));
    }
}
