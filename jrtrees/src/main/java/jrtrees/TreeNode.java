package jrtrees;

/**
 * 
 * @author jregan
 *
 */
public class TreeNode {

    private TreeNode[] children;
    
    /**
     * 
     * @param children
     */
    public TreeNode(TreeNode[] children) {
        this.children = children;
    }

    
    public TreeNode[] getChildren() {
        return children;
    }

    public void setChildren(TreeNode[] children) {
        this.children = children;
    }
    
    public int getNumChildren() {
        return children.length;
    }
    
    public TreeNode getChild(int i) {
        if(children != null && children.length > i) {
            return children[i];
        } else {
            return null;
        }
    }
    
}
