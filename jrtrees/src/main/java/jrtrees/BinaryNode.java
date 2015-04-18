package jrtrees;

import java.util.Queue;

/**
 * 
 * @author jregan
 * 
 */
public class BinaryNode {

    public BinaryNode left;

    public BinaryNode right;

    public int value;

    /**
     * 
     */
    public BinaryNode() {
        left = null;
        right = null;

        value = 0;
    }

    /**
     * 
     * @param val
     */
    public BinaryNode(int val) {
        left = null;
        right = null;
        value = val;
    }

    /**
     * 
     * @param node
     */
    public void addChild(BinaryNode node) {
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
     * @param numNodesFound
     * @return
     */
    public int preorderTraverse() {
        
        int numNodesFound = 1;
        
        if (left != null) {
            numNodesFound += left.preorderTraverse();
        }

        if (right != null) {
            numNodesFound += right.preorderTraverse();
        }
        
        return numNodesFound;
        
    }
    
    /**
     * 
     * @param treeArray
     * @return
     */
    public int[] preorderInsert(IndexAccumulator accum, int[] treeArray) {
       
        treeArray[accum.index] = this.value;
        accum.index++;
        
        if (left != null) {
            left.preorderInsert(accum, treeArray);
        }

        if (right != null) {
            right.preorderInsert(accum, treeArray);
        }
        
        return treeArray;
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
    public void bfsPrint(Queue<BinaryNode> q) {

        q.add(this);

        while (q.size() != 0) {
            
            BinaryNode node = q.remove();

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
