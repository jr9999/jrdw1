package jrtrees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author jregan
 * 
 */
public class BinaryTree {

    public BinaryNode parent;

    /**
     * 
     */
    public BinaryTree() {
        parent = null;
    }

    /**
     * 
     * @param node
     */
    public void setParent(BinaryNode node) {
        parent = node;
    }

    /**
     * 
     * @param node
     */
    public void addNode(BinaryNode node) {
        if (parent == null) {
            node = parent;
        } else {
            parent.addChild(node);
        }
    }

    /**
     * 
     */
    public void preorderPrint() {
        if (parent != null) {
            parent.preorderPrint();
        }
    }

    /**
     * 
     */
    public void inorderPrint() {
        if (parent != null) {
            parent.inorderPrint();
        }
    }

    /**
     * 
     */
    public void postorderPrint() {
        if (parent != null) {
            parent.postorderPrint();
        }
    }

    /**
     * 
     */
    public void bfsPrint() {
        
        if (parent != null) {
            
            Queue<BinaryNode> q = new LinkedList<BinaryNode>();
            
            parent.bfsPrint(q);
        }
    }
    
    /**
     * 
     * @return
     */
    public int[] toArray() {
        //first traversal to get # of elements.
        
        int numNodes = parent.preorderTraverse();
        
        int[] treeArray = new int[numNodes];
        
        IndexAccumulator accum = new IndexAccumulator();
        accum.index = 0;
        
        
        treeArray = parent.preorderInsert(accum, treeArray);
        
        return treeArray;
    }
}
