package com.datastructurediagrammer.trees;

public class BinarySearchTree<T extends Comparable<T>> {
    public BSTNode<T> root;

    public BinarySearchTree() { 
        root = null;
    }

    public BinarySearchTree(BSTNode<T> initRoot) { 
        root = initRoot;
    }

    /**
     * 
     * @param newData
     */
    public void insert(T newData) { 
        BSTNode<T> newNode = new BSTNode<T>(newData);
        BSTNode<T> currentNode = root;
        if (root == null) { 
            root = newNode;
            return;
        }
        while (currentNode != null) { 
            if (newNode.compareTo(currentNode) < 0) { 
                if (currentNode.left == null) {
                    currentNode.left = newNode;
                    currentNode = null;
                } else { 
                    currentNode = currentNode.left;
                }
            } else {
                if (currentNode.right == null) { 
                    currentNode.right = newNode;
                    currentNode = null;
                } else {
                    currentNode = currentNode.right;
                }
            }
        }
    }

    // Insert array shortcut method makes writing tests easier. Inserts in order.
    public void insert(T[] objects) { 
        for (int i = 0; i < objects.length; ++i) { 
            this.insert(objects[i]);
        }
    }

    /**
     * Removes the BSTNode containing the given search key from the tree. 
     * @param dataRemove the key to be removed from the tree
     * @return boolean success of the removal
     */
    public boolean remove(T dataRemove) { 
        BSTNode<T> parent = null;
        BSTNode<T> curr = root;
        while (curr != null) {  // Node found
            if (curr.getData().equals(dataRemove)) { 
                // Case 1: Leaf
                if ((!(curr.left != null)) && (!(curr.right != null))) { 
                    if (!(parent != null)) { // Node is root
                        this.root = null; // List is now empty
                    } else if (parent.left == curr) { 
                        parent.left = null;
                    } else { 
                        parent.right = null;
                    }
                }
                // Case 2: Node with only left child
                else if (!(curr.right != null)) { 
                    if (!(parent != null)) { // node is root
                        root = curr.left;
                    } else if (parent.left == curr) { 
                        parent.left = curr.left;
                    } else { 
                        parent.right = curr.left;
                    }
                }
                // Case 3: Node with only right child
                else if (!(curr.left != null)) { 
                    if (!(parent != null)) { // node is root
                        root = curr.right;
                    } else if (parent.left == curr) { 
                        parent.left = curr.right;
                    } else { 
                        parent.right = curr.right;
                    }
                }
                // Case 4: Node with two children
                else { 
                    // Find successor - leftmost child of right subtree.
                    BSTNode<T> successor = curr.right;
                    while (successor.left != null) { 
                        successor = successor.left;
                    }
                    T successorData = successor.getData();
                    remove(successorData);
                    curr.setData(successorData);
                }
                return true;
            }
            else if (curr.getData().compareTo(dataRemove) < 0) { 
                parent = curr;
                curr = curr.right;
            } else { 
                parent = curr;
                curr = curr.left;
            }
        }
        return false;
    }

    /**
     * Returns the first BSTNode whose data matches the search key.
     * @param searchKey
     * @return BSTNode<T> the first match or null.
     */
    public BSTNode<T> search(T searchKey) { 
        BSTNode<T> currentNode = root;
        while (currentNode != null) { 
            if (currentNode.getData() == searchKey) { 
                return currentNode;
            } else if (currentNode.getData().compareTo(searchKey) < 0) { 
                currentNode = currentNode.left;
            } else if (currentNode.getData().compareTo(searchKey) > 0) { 
                currentNode = currentNode.right;
            }
        }
        return null;
    }

    /**
     * Print tree elements in order.
     * @param node root of tree or subtree to print in ascending order
     */
    public void printInOrder(BSTNode<T> node) { 
        if (node == null) { 
            return;
        }
        printInOrder(node.left);
        System.out.println(node.getData() + " ");
        printInOrder(node.right);
    }

    /**
     * 
     * @return int number of levels of the tree
     */
    public int getHeight() {
        if (root == null) { 
            return 0;
        } else { 
            return maxDepth(this.root) + 1;
        }
    }

    /**
     * 
     * @param node
     * @return
     */
    public int maxDepth(BSTNode<T> node) { 
        if (node == null ) { return -1; }
        int leftHeight = maxDepth(node.left);
        int rightHeight = maxDepth(node.right);
        int maxDepth = Math.max(leftHeight, rightHeight) + 1;
        return maxDepth; 
    }

    public int getTotalNodes() { 
        return countNodes(0, root);
    }

    private int countNodes(int count, BSTNode<T> node) {
        if (node == null) { 
            return 0;
        } else {
            return countNodes(count, node.left) + count + 1 + countNodes(count, node.right);
        }
    }
}