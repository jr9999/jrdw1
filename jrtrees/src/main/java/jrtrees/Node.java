package jrtrees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author jregan
 * 
 */
public class Node {

    public Node left;

    public Node right;

    public int value;

    /**
     * 
     */
    public Node() {
        left = null;
        right = null;

        value = 0;
    }

    /**
     * 
     * @param val
     */
    public Node(int val) {
        left = null;
        right = null;
        value = val;
    }

    /**
     * 
     * @param node
     */
    public void addChild(Node node) {
        if (this.value < node.value) {
            if (right == null) {
                right = node;
            } else {
                right.addChild(node);
            }
        } else {
            if (left == null) {
                left = node;
            } else {
                left.addChild(node);
            }
        }
    }

    /**
     * 
     */
    public void preorderPrint() {

        System.out.println(this.value);

        if (left != null) {
            left.preorderPrint();
        }

        if (right != null) {
            right.preorderPrint();
        }
    }

    /**
     * 
     */
    public void inorderPrint() {

        if (left != null) {
            left.inorderPrint();
        }

        System.out.println(this.value);

        if (right != null) {
            right.inorderPrint();
        }
    }

    /**
     * 
     */
    public void postorderPrint() {

        if (left != null) {
            left.postorderPrint();
        }

        if (right != null) {
            right.postorderPrint();
        }

        System.out.println(this.value);
    }

    /**
     * @param q
     * 
     */
    public void bfsPrint(Queue<Node> q) {

        q.add(this);

        while (q.size() != 0) {
            
            Node node = q.remove();

            System.out.println(node.value);

            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }
}
