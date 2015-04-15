package jrtrees;

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

        /*
        BinarySearchTree bst = new BinarySearchTree();
        
        Node parent = new Node(10);
        
        bst.setParent(parent);
        
        Node node2 = new Node(4);
        bst.addNode(node2);
        
        Node node21 = new Node(1);
        bst.addNode(node21);
        
        Node node22 = new Node(5);
        bst.addNode(node22);
        
        Node node3 = new Node(12);
        bst.addNode(node3);
        
        Node node31 = new Node(11);
        bst.addNode(node31);
        
        Node node32 = new Node(15);
        bst.addNode(node32);
        
        System.out.println("PREORDER");
        bst.preorderPrint();
        
        
        System.out.println("INORDER");
        bst.inorderPrint();
        
        System.out.println("POSTORDER");
        bst.postorderPrint();
        
        System.out.println("LEVELPRINT");
        bst.bfsPrint();
        */
        
        char[] start = new char[8];
        start[0] = 'a';
        start[1] = 'b';
        start[2] = 'c';
        start[3] = 'd';
        start[4] = 'e';
        start[5] = 'f';
        start[6] = 'g';
        start[7] = 'h';
        
        StringRotator rot = new StringRotator(start, 3);
        
        char[] result = rot.rotate1();
        
        char[] start2 = new char[8];
        start2[0] = 'a';
        start2[1] = 'b';
        start2[2] = 'c';
        start2[3] = 'd';
        start2[4] = 'e';
        start2[5] = 'f';
        start2[6] = 'g';
        start2[7] = 'h';
        
        StringRotator rot2 = new StringRotator(start2, 3);
        
        char[] result2 = rot2.rotate2();
        
    }

}
