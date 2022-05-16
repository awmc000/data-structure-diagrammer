package com.datastructurediagrammer.trees;

public class Trie {
    public TrieNode[] head;

    public Trie() { 
        head = new TrieNode[26];
    }

    public void add (String newString) { 
        // Fix up the string
        newString = newString.toUpperCase();
        // TODO: Get rid of numbers and non-alphabet characters.

        // Split the string into a char array
        char[] charArray = newString.toCharArray();

        // Set up the integer array to hold the codes of 0 to 25
        int[] newStringCodes = new int[charArray.length];

        // Fill that array with the codes, using a static method that subtracts 65
        for (int i = 0; i < charArray.length; ++i) { 
            newStringCodes[i] = charCode(charArray[i]);
        }

        boolean oneLetterWord = false;
        if (newString.length() == 1) { 
            oneLetterWord = true;
        }

        head[newStringCodes[0]] = new TrieNode(oneLetterWord);
        TrieNode currNode = head[newStringCodes[0]];

        for (int i = 1; i < newStringCodes.length; ++i) { 
            
            int nextCode = newStringCodes[i];
            boolean nextIsTerminal = false;

            // If the string code we are currently processing is the last one in the string, be sure to flag it as terminal.
            if (i == newStringCodes.length - 1) { 
                nextIsTerminal = true;
            }

            // Add the current *character*.
            currNode.add(new TrieNode(nextIsTerminal), nextCode, nextIsTerminal);

            // Continue the traversal onto the next character in the sequence (Next character in string being added).
            currNode = currNode.links[nextCode];
        }
    }

    public boolean contains(String searchKey) { 
        char[] charArray = searchKey.toCharArray();
        int[] codeArray = new int[charArray.length];

        for (int i = 0; i < codeArray.length; ++i) { 
            codeArray[i] = charCode(charArray[i]); // Eg.: "HEAL" -> charArray[2] == 'A', charChode(charArray[2]) == 0
        }

        TrieNode currNode = head[codeArray[0]];

        for (int i = 1; i < codeArray.length; ++i) {
            if (currNode != null) {
                if (currNode.links[codeArray[i]] != null) { 
                    currNode = currNode.links[codeArray[i]];
                }
            }
        }

        return false;
    }

    public static int charCode(Character character) { 
        return character - 65;
    }
    public static void main(String[] args) { 
        Trie testTrie = new Trie();
        testTrie.add("HELLO");
        System.out.println(testTrie.contains("HELLO") + " " + testTrie.contains("A"));
        testTrie.add("A");

        System.out.println(testTrie.contains("HELLO") + " " + testTrie.contains("A"));
        
    }

}
