package jrtrees;

public class IntTreeNode extends TreeNode {

    private int value;

    /**
     * 
     * @param children
     * @param value
     */
    public IntTreeNode(TreeNode[] children, int value) {
        super(children);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
