package jrtrees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author jregan
 * 
 */
public class BinarySearchTree {

    public Node parent;

    /**
     * 
     */
    public BinarySearchTree() {
        parent = null;
    }

    /**
     * 
     * @param node
     */
    public void setParent(Node node) {
        parent = node;
    }

    /**
     * 
     * @param node
     */
    public void addNode(Node node) {
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
            
            Queue<Node> q = new LinkedList<Node>();
            
            parent.bfsPrint(q);
        }
    }
}
