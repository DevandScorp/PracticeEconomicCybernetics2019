import javax.swing.tree.DefaultTreeModel;

public class TreeModel extends DefaultTreeModel {

    private static final long serialVersionUID = 1L;
    private TreeNode root;

    TreeModel(TreeNode root) {
        super(root);
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    void insertNodeInto(TreeNode child, TreeNode parent, int i, boolean flag) {
        insertNodeInto(child, parent, i);
        parent.addNode(child);
    }

}