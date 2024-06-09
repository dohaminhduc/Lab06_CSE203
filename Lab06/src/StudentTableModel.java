import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class StudentTableModel extends JScrollPane {
    private DefaultTableModel model;
    private JTable table;
    public StudentTableModel() {
        init();
    }
    private void init(){
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Gender");
        model.addColumn("School Stage");

        table = new JTable(model);
        fillStudentTable(MainFrame.studentManager.students);
        setViewportView(table);
    }

    public void fillStudentTable(ArrayList<Student> students) {
        model.setRowCount(0); // Clear existing rows
        for (Student student : students) {
            model.addRow(new String[]{
                    student.getStudentID(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getGender(),
                    student.getSchoolStage()
            });
        }
    }
    public void deleteStudentData() {
        int row = table.getSelectedRow();
        int column = 0;
        if (row != -1) {
            String ID = (String) model.getValueAt(row, column);
            model.removeRow(row);
            MainFrame.studentManager.deleteStudent(ID);
        }
    }
}
