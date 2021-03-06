package com.datastructurediagrammer.trees;

import java.util.List;

public class Trie {
    public TrieNode[] head;

    public Trie() { 
        head = new TrieNode[26];
    }

    /**
     * Adds word to trie. Utilizes the TrieNode.add() method to add a link to the next node to each interal node.
     * @param newString A word to be added to the trie. Ideal input is uppercase and alphabet only. capability to handle spaces coming soon.
     */
    public void add (String newString) { 
        // Fix up the string
        newString = fix(newString, false);
        
        // Get rid of numbers and non-alphabet characters. TODO: allow spaces.
        String filter = "";
        for (int i = 0; i < newString.length(); i++) { 
            if (Character.isAlphabetic(newString.charAt(i))) { 
                filter += Character.toString(newString.charAt(i));
            }
        }
        newString = filter;

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

    /**
     * Helper method which adds an array of strings. Makes test cases cleaner.
     * @param newStrings
     */
    public void add(String[] newStrings) { 
        for (int i = 0; i < newStrings.length; ++i) { 
            this.add(newStrings[i]);
        }
    }

    /**
     * Checks whether the Trie contains a given word.
     * @param searchKey Word to search for.
     * @return boolean Trie contains or does not contain search key.
     */
    public boolean contains(String searchKey) {
        searchKey = fix(searchKey, false);
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
            } else { 
                return false;
            }
        }

        return true;
    }

    /**
     * Helper method to contains(String) which checks the trie for each word in the given array in order.
     * @param searchKeys array
     * @return Trie contains or does not contain search keys.
     */
    public boolean contains(String[] searchKeys) { 
        for (int i = 0; i < searchKeys.length; ++i) { 
            if (!(this.contains(searchKeys[i]))) { 
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method to contains(String[]) which takes a List type, converts it to array, passes it in.
     * @param searchKeysList List
     * @return boolean Trie contains or does not contain search keys.
     */
    public <T> boolean contains(List<T> searchKeysList) { 
        String[] searchKeys = (String[]) searchKeysList.toArray();
        return this.contains(searchKeys);
    }

    /**
     * Helper method
     * @param character
     * @return the integer from 0 to 25 representing the
     * given letter 
     */
    public static int charCode(Character character) { 
        return character - 65;
    }

    /**
     * Cleans up a given string, removing unacceptable characters
     * @param rawString
     * @param spacesAllowed
     * @return
     */
    public static String fix(String rawString, boolean spacesAllowed) {
        // Capitalize
        rawString = rawString.toUpperCase();
        
        // First, get rid of spaces, if not allowed.
        if (!spacesAllowed) {
            rawString += rawString.replace(" ", "");
        }
        
        String newString = "";
        
        
        // Get rid of not alphabet chars 
        for (int i = 0; i < rawString.length(); ++i) { 
            char current = rawString.charAt(i);
            // Check that character is either: 1. English Alphabet letter or 2. it is a space, and spaces are allowed.
            if ((Character.isLetter(current) || ((current == ' ') && spacesAllowed))) { 
                newString += Character.toString(rawString.charAt(i));
            }
        }


        return newString;
    }

}
