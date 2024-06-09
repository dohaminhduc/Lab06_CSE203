import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CDTableModel extends JScrollPane {
    private Management management = Management.getInstance();
    private DefaultTableModel model;
    private JTable table;
    public CDTableModel(){
        init();
    }
    private void init(){
        model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn(" Collection");
        model.addColumn("Type");
        model.addColumn("Price");

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        fillCDTable(management.getCD());
        setViewportView(table);
    }
    public void fillCDTable(ArrayList<CD> list){
        model.setRowCount(0);
        for(CD cd : list){
            model.addRow(new String[]{
                    cd.getTitle(),
                    cd.getCollection(),
                    cd.getType(),
                    String.valueOf(cd.getPrice())
            });
        }
    }
    public void deleteCD(){
        int row = table.getSelectedRow();
        int column = 0;
        if (row != -1){
            String title = (String) model.getValueAt(row, column);
            model.removeRow(row);
            management.deleteByTitle(title);
        }
    }
}
