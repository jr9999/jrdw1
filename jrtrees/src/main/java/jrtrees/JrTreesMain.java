package jrtrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author jregan
 * 
 */
public class JrTreesMain {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {

        BinaryTree bst = new BinaryTree();

        // inserted order : 10, 4, 1, 5, 12, 11, 15

        // tree order: 10, 4, 1, 5, 12, 11, 15

        BinaryNode parent = new BinaryNode(10);

        bst.setParent(parent);

        BinaryNode node2 = new BinaryNode(4);
        bst.addNode(node2);

        BinaryNode node21 = new BinaryNode(1);
        bst.addNode(node21);

        BinaryNode node22 = new BinaryNode(5);
        bst.addNode(node22);

        BinaryNode node3 = new BinaryNode(12);
        bst.addNode(node3);

        BinaryNode node31 = new BinaryNode(11);
        bst.addNode(node31);

        BinaryNode node32 = new BinaryNode(15);
        bst.addNode(node32);

        /*
         * System.out.println("PREORDER");
         * bst.preorderPrint();
         * 
         * 
         * System.out.println("INORDER");
         * bst.inorderPrint();
         * 
         * System.out.println("POSTORDER");
         * bst.postorderPrint();
         * 
         * System.out.println("LEVELPRINT");
         * bst.bfsPrint();
         */

        int[] arr = bst.toArray();

        Arrays.sort(arr);

        System.out.println("The sorted int array is:");

        for (int number : arr) {
            System.out.println("Number = " + number);
        }

        // children in the array are at
        // left = 2x + 1
        // right = 2x + 2
        BinaryTree bHeap = new BinaryTree();

        Map<Integer, BinaryNode> assigned = new HashMap<Integer, BinaryNode>();

        for (int i = 0; i < arr.length; i++) {

            if (!assigned.containsKey(i)) {

                BinaryNode bHeapNode = new BinaryNode();
                bHeapNode.value = arr[i];
                assigned.put(i, bHeapNode);

                if (i == 0) {
                    bHeap.parent = bHeapNode;
                }
                
                int left = 2 * i + 1;
                int right = left + 1;

                if (left < arr.length) {
                    bHeapNode.left = new BinaryNode();
                    bHeapNode.left.value = arr[left];
                    assigned.put(left, bHeapNode.left);
                }

                if (right < arr.length) {
                    bHeapNode.right = new BinaryNode();
                    bHeapNode.right.value = arr[2 * i + 2];
                    assigned.put(right, bHeapNode.right);
                }
            } else {

                BinaryNode bHeapNode = assigned.get(i);

                int left = 2 * i + 1;
                int right = left + 1;

                if (left < arr.length) {
                    bHeapNode.left = new BinaryNode();
                    bHeapNode.left.value = arr[left];
                    assigned.put(left, bHeapNode.left);
                }

                if (right < arr.length) {
                    bHeapNode.right = new BinaryNode();
                    bHeapNode.right.value = arr[2 * i + 2];
                    assigned.put(right, bHeapNode.right);
                }
            }
        }

        bHeap.preorderPrint();

        /*
         * char[] start = new char[8];
         * start[0] = 'a';
         * start[1] = 'b';
         * start[2] = 'c';
         * start[3] = 'd';
         * start[4] = 'e';
         * start[5] = 'f';
         * start[6] = 'g';
         * start[7] = 'h';
         * 
         * StringRotator rot = new StringRotator(start, 3);
         * 
         * char[] result = rot.rotate1();
         * 
         * char[] start2 = new char[8];
         * start2[0] = 'a';
         * start2[1] = 'b';
         * start2[2] = 'c';
         * start2[3] = 'd';
         * start2[4] = 'e';
         * start2[5] = 'f';
         * start2[6] = 'g';
         * start2[7] = 'h';
         * 
         * StringRotator rot2 = new StringRotator(start2, 3);
         * 
         * char[] result2 = rot2.rotate2();
         * 
         * char[] start3 = new char[8];
         * start3[0] = 'a';
         * start3[1] = 'b';
         * start3[2] = 'c';
         * start3[3] = 'd';
         * start3[4] = 'e';
         * start3[5] = 'f';
         * start3[6] = 'g';
         * start3[7] = 'h';
         * 
         * StringRotator rot3 = new StringRotator(start3, 3);
         * 
         * //char[] result3 = rot3.rotate3();
         */

        /*
         * BinarySearchAlg bsa = new BinarySearchAlg();
         * 
         * boolean found = bsa.search(52);
         */

    }

}
