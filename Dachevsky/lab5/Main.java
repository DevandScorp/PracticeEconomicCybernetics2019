import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    public static Node addResult = null;
    public static String path = null;
    JTable infoPanel = new JTable();
    JTree notebooksTree = new JTree();
    TreeTableModel treeTableModel = null;
    TreeModel treeModel = null;

    public Main() throws HeadlessException {

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> openAddDialog());

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> removeItem());

        treeTableModel = new TreeTableModel();
        infoPanel = new JTable(treeTableModel);
        treeModel = new TreeModel(new TreeNode("Directory"));
        notebooksTree = new JTree(treeModel);
        notebooksTree.addTreeSelectionListener(e -> {
            TreeNode node = (TreeNode) notebooksTree.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }
            ArrayList<Node> array = node.getAllLeaves();
            treeTableModel = new TreeTableModel(array);
            infoPanel.setModel(treeTableModel);
        });
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, new JScrollPane(notebooksTree), new JScrollPane(infoPanel));
        splitPane.setDividerLocation(300);

        //path = "input.txt";
        //readInfo(path);

        getContentPane().add(splitPane);
        getContentPane().add("North", addButton);
        getContentPane().add("South", removeButton);
        setBounds(100, 100, 600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void openAddDialog() {
        CreateObjectForm addForm = new CreateObjectForm(this);
        addForm.setVisible(true);
    }

    void addNewItem() {
        TreeNode temp, root = treeModel.getRoot(), insert, where;
        if (addResult != null) {
            try {
                if (findNode(root, addResult.getShopPart()) == null) {
                    treeModel.insertNodeInto(new TreeNode(addResult.getShopPart()), root, root.getChildCount(), false);
                    treeModel.insertNodeInto(new TreeNode(addResult.getGenre()), temp = (findNode(root, addResult.getShopPart())), temp.getChildCount(), false);
                    treeModel.insertNodeInto(new TreeNode(addResult.getName()), temp = (findNode(temp, addResult.getGenre())), temp.getChildCount(), false);
                    treeModel.insertNodeInto(new TreeNode(Integer.toString(addResult.getPrice()), true), temp = (findNode(temp, addResult.getName())), temp.getChildCount(), false);

                } else if (findNode(root, addResult.getGenre()) == null) { //отдел есть, жанра нет
                    treeModel.insertNodeInto(new TreeNode(addResult.getGenre()), temp = (findNode(root, addResult.getShopPart())), temp.getChildCount(), false);
                    treeModel.insertNodeInto(new TreeNode(addResult.getName()), temp = (findNode(root, addResult.getGenre())), temp.getChildCount(), false);
                    treeModel.insertNodeInto(new TreeNode(Integer.toString(addResult.getPrice()), true), temp = (findNode(root, addResult.getName())), temp.getChildCount(), false);
                } else if (findNode(root, addResult.getName()) == null) {
                    temp = findNode(root, addResult.getShopPart());
                    treeModel.insertNodeInto(new TreeNode(addResult.getName()), temp = (findNode(temp, addResult.getGenre())), temp.getChildCount(), false);
                    treeModel.insertNodeInto(new TreeNode(Integer.toString(addResult.getPrice()), true), temp = (findNode(temp, addResult.getName())), temp.getChildCount(), false);
                } else {
                    temp = findNode(root, addResult.getShopPart());
                    temp = findNode(temp, addResult.getGenre());
                    treeModel.insertNodeInto(new TreeNode(Integer.toString(addResult.getPrice()), true), temp = (findNode(temp, addResult.getName())), temp.getChildCount(), false);
                }
            } catch (Exception e) {
                path = null;
                addResult = null;
                return;
            }
        }
        addResult = null;
    }

    public void removeItem() {
        TreePath currentSelection = notebooksTree.getSelectionPath();
        if (currentSelection != null) {
            TreeNode currentNode = (TreeNode) (currentSelection.getLastPathComponent());
            TreeNode parent = (TreeNode) (currentNode.getParent());
            if (parent != null) {
                treeModel.removeNodeFromParent(currentNode);
                parent.deleteNode(currentNode);
                ArrayList<Node> array = parent.getAllLeaves();
                treeTableModel = new TreeTableModel(array);
                infoPanel.setModel(treeTableModel);
            }
        }
    }


    private TreeNode findNode(TreeNode root, String s) {
        Enumeration<TreeNode> e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            TreeNode node = e.nextElement();
            if (node.toString().equalsIgnoreCase(s)) {
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            System.err.println(e.getMessage());
        }
        Main mainClass = new Main();
        mainClass.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainClass.setVisible(true);
    }
}