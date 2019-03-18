import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TreeNode extends DefaultMutableTreeNode {

    private static final long serialVersionUID = 1L;
    private String nodeName;
    private ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
    boolean isLeafNode = false;

    public TreeNode() {
        nodeName = "-";
    }

    TreeNode(String name) {
        nodeName = name;
    }

    TreeNode(String name, boolean LeafNode) {
        nodeName = name;
        isLeafNode = LeafNode;
    }

    void addNode(TreeNode node) {
        nodes.add(node);
    }

    boolean deleteNode(TreeNode node) {
        boolean isExist = false;
        for (int i = 0; i < nodes.size(); ++i)
            if (nodes.get(i).toString().compareToIgnoreCase(node.toString()) == 0) {
                nodes.remove(i);
                isExist = true;
            }
        return isExist;
    }

    ArrayList<Node> getAllLeaves() {
        ArrayList<Node> leaves = new ArrayList<Node>();
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
        deque.push(this);
        TreeNode temp;
        while (!deque.isEmpty()) {
            temp = deque.removeFirst();
            if (temp.isLeafNode) {
                int i = 0;
                String info[] = new String[4];
                while (temp.getParent() != null) {
                    info[i++] = temp.nodeName;
                    temp = (TreeNode) temp.getParent();
                }
                leaves.add(new Node(info[3], info[2], info[1], info[0]));
            } else
                for (int i = 0; i < temp.nodes.size(); ++i)
                    deque.push(temp.nodes.get(i));
        }
        return leaves;
    }

    public String toString() {
        return nodeName;
    }

}