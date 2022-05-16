package com.datastructurediagrammer.trees;

public class Trie {
    public Character[] head;

    public Trie() { 
        head = new Character[26];
    }

    public void add (String newString) { 
        char[] newChars = newString.toCharArray();
        Character[] currentArray = head;
        for (char character : newChars) { 
            Character currentCharacter = character;
        }
        
    }

    public static int charCode(Character character) { 
        return character - 66;
    } 
}
