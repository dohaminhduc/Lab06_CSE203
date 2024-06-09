import javax.swing.*;
import java.awt.*;

public class NewStudentDialog extends JDialog {
    public NewStudentDialog(){
        init();
    }
    private void init(){
        setTitle("New Student");
        setSize(375, 200);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblID = new JLabel("Student ID: ");
        JTextField tfID = new JTextField(25);
        JLabel lblName = new JLabel("Name: ");
        JTextField tfLastName = new JTextField(8);
        JTextField tfFirstName = new JTextField(8);
        JTextField tfMidName = new JTextField(8);
        JLabel lblYearOfBirth = new JLabel("Year Of Birth: ");
        JTextField tfYOB = new JTextField(10);

        JRadioButton btnMale = new JRadioButton("Male");
        JRadioButton btnFemale = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(btnMale);
        bg.add(btnFemale);

        JComboBox schoolsCombo = new JComboBox(new String[]{"Elementary School", "Middle School", "High School"});
        JButton btnSave = new JButton("Save");
        JButton btnCancel = new JButton("Cancel");
        JButton btnClear = new JButton("Clear");

        //Function of buttons
        btnSave.addActionListener(e ->{
            String id = tfID.getText();
            String name = tfFirstName.getText();
            String lastName = tfLastName.getText();
            String midName = tfMidName.getText();
            String gender = "null";
            if (btnMale.isSelected()){
                gender = "Male";
            } else if (btnFemale.isSelected()){
                gender = "Female";
            }
            String school = schoolsCombo.getSelectedItem().toString();
            try {
                int YOB = Integer.parseInt(tfYOB.getText());
                Student student = new Student(id, lastName, name, midName, YOB, gender, school);
                MainFrame.studentManager.addStudent(student);
                JOptionPane.showMessageDialog(null, "Student Added Successfully");
            }catch (NumberFormatException numE){
                JOptionPane.showMessageDialog(this, "Year Of Birth Must be a Number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnClear.addActionListener(e -> {
            tfID.setText("");
            tfFirstName.setText("");
            tfLastName.setText("");
            tfMidName.setText("");
            tfYOB.setText("");
            schoolsCombo.setSelectedIndex(0);
        });
        btnCancel.addActionListener(e -> {
            dispose();
        });

        panel.add(lblID);
        panel.add(tfID);
        panel.add(lblName);
        panel.add(tfLastName);
        panel.add(tfMidName);
        panel.add(tfFirstName);
        panel.add(lblYearOfBirth);
        panel.add(tfYOB);
        panel.add(btnMale);
        panel.add(btnFemale);

        panel.add(Box.createHorizontalStrut(100));
        panel.add(schoolsCombo);
        panel.add(Box.createHorizontalStrut(100));


        panel.add(btnSave);
        panel.add(btnClear);
        panel.add(btnCancel);

        add(panel);
    }
}