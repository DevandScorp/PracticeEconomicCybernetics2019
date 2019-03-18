import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TreeTableModel implements javax.swing.table.TableModel {

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private ArrayList<Node> infoNodes = new ArrayList<Node>();
    static final String[] columnNames = new String[]{"Секция", "Доклад", "Докладчики", "Номер доклада"};
    static final Class<?>[] columnsections = new Class[]{String.class, String.class, String.class, Integer.class};

    public TreeTableModel() {
    }

    public TreeTableModel(ArrayList<Node> al) {
        setInfoNodes(al);
    }

    public void setInfoNodes(ArrayList<Node> al) {
        infoNodes = al;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnsections[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return infoNodes.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Node an = infoNodes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return an.getShopPart();
            case 1:
                return an.getGenre();
            case 2:
                return an.getName();
            case 3:
                return an.getPrice();
        }
        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return false;
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

}