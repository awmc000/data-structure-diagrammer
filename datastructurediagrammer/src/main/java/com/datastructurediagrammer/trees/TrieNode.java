package com.datastructurediagrammer.trees;

/**
 * Node class for Trie.
 */
public class TrieNode {
    public TrieNode[] links; // An array of length 26 - A to Z uppercase
    public boolean isTerminal;

    public TrieNode(boolean isTerminal) { 
        this.isTerminal = isTerminal;
        this.links = new TrieNode[27];
    }

    /**
     * Adds a connection to another TrieNode to this TrieNode.
     * The index it is placed at is what letter it will represent, from 0 = A to 25 = Z.
     * @param newNode
     * @param index
     * @param terminal
     * @return
     */
    public boolean add(TrieNode newNode, int index, boolean terminal) { 
        // If the index is inappropriate, return false
        if ((index < 0) || (index > 25)) { 
            return false;
        }

        // If the desired spot in the links array is empty, put it there.
        if (links[index] != null) { 
            // The desired link was already there. Cool
            links[index].isTerminal = terminal;
            return true;
        } else { 
            // The next link is successfully inserted.
            links[index] = newNode;
            links[index].isTerminal = terminal; // If adding a terminal node, flag it as such.
            return true;
        }
    }
}
