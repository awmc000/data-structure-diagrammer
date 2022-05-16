package com.datastructurediagrammer.trees;

public class TrieNode {
    public Character character;
    public Character[] next; // An array of length 27 - A to Z uppercase and empty space.
    public boolean isTerminal;

    public TrieNode(Character character, boolean isTerminal) { 
        this.character = character;
        this.isTerminal = isTerminal;
        this.next = new Character[27];
    }

    public void add(Character character) { 
        int code = character - 66;

        if (next[code] != null) { 
            next[code] = character;
        }
    }
}
