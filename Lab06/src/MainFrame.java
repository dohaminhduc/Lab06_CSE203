import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


    public class MainFrame extends JFrame {
        public static StudentManager studentManager = new StudentManager();
        private StudentTableModel studentTableModel;

        public MainFrame() {
            init();
        }

        private void init() {
            setTitle("Student Manager");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(new Dimension(550, 600));
            setLocationRelativeTo(null);
            setResizable(false);


            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton checkTotalStudentsBTN = new JButton("Check Total Students");;
            JButton newStudentBTN = new JButton("New Student");
            JButton refreshBTN = new JButton("Refresh");
            JButton deleteBTN = new JButton("Delete");
            studentTableModel = new StudentTableModel();
            add(panel, BorderLayout.NORTH);
            panel.add(checkTotalStudentsBTN);
            panel.add(newStudentBTN);
            panel.add(refreshBTN);
            panel.add(deleteBTN);
            panel.add(studentTableModel);

            //Check total students function
            checkTotalStudentsBTN.addActionListener(e -> {
                ArrayList<Student> students = studentManager.getStudents();
                JOptionPane.showMessageDialog(null, "Total Students: " + students.size());
            });

            //Add Student function
            newStudentBTN.addActionListener(e -> {
                NewStudentDialog newStudentDialog = new NewStudentDialog();
                newStudentDialog.setVisible(true);
            });
            refreshBTN.addActionListener(e -> {
                ArrayList<Student> students = studentManager.getStudents();
                studentTableModel.fillStudentTable(students);
            });
            deleteBTN.addActionListener(e -> {
                studentTableModel.deleteStudentData();
            });
        }

        public static void main(String[] args) {
            EventQueue.invokeLater(() -> {
                new MainFrame().setVisible(true);
            });
        }

    }
