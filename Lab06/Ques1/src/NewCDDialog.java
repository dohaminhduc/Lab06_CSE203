import javax.swing.*;
import java.awt.*;

public class NewCDDialog extends JDialog {
    public NewCDDialog() {
        init();
    }
    private void init(){
        setTitle("New CD");
        setSize(345,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblID = new JLabel("CD ID");
        lblID.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField tfID = new JTextField(20);

        JLabel lblTitle = new JLabel("CD Title");
        JTextField tfTitle = new JTextField(20);

        JLabel lblCollection = new JLabel("CD Collection");
        JComboBox Collection = new JComboBox(new String[] {"Game","Movie","Music"});
        Collection.setBounds(new Rectangle(10,20));

        JLabel lblType = new JLabel("CD Type");
        JRadioButton VCDbtn = new JRadioButton("VCD");
        JRadioButton CDbtn = new JRadioButton("CD");
        ButtonGroup bg = new ButtonGroup();
        bg.add(VCDbtn);
        bg.add(CDbtn);

        JLabel lblPrice = new JLabel("CD Price");
        lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField tfPrice = new JTextField(22);

        JLabel lblYear = new JLabel("CD Year of Release");
        lblYear.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField tfYear = new JTextField(22);

        JButton addBTN = new JButton("Add");
        JButton clearBTN = new JButton("Clear");
        JButton showBTN = new JButton("Show all");

        addBTN.addActionListener(e-> {
            String collection = null;
            String type = null;

            switch (Collection.getSelectedIndex()) {
                case 0:
                    collection = "Game";
                    break;
                case 1:
                    collection = "Movie";
                    break;
                case 2:
                    collection = "Music";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Please select a valid collection type");
                    return;
            }
            if (VCDbtn.isSelected()) {
                type = "VCD";
            } else {
                type = "CD";
            }
            String title = tfTitle.getText().trim();
            String id = tfID.getText().trim();
            if (title.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Title and ID cannot be empty");
                return;
            }
            double price;
            int year;
            try {
                price = Double.parseDouble(tfPrice.getText().trim());
                year = Integer.parseInt(tfYear.getText().trim());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Make sure that Price and Year are valid numbers");
                return;
            }
            CD cd = new CD(title, type, id, price, year, collection);
            CDFrame.management.addCD(cd);
            JOptionPane.showMessageDialog(null, "CD Added Successfully");
            tfID.setText("");
            tfTitle.setText("");
            tfPrice.setText("");
            tfYear.setText("");
            Collection.setSelectedIndex(0);
            VCDbtn.setSelected(false);
            CDbtn.setSelected(false);
        });
        clearBTN.addActionListener(e->{
            tfID.setText("");
            tfTitle.setText("");
            tfPrice.setText("");
            tfYear.setText("");
            Collection.setSelectedIndex(0);
            VCDbtn.setSelected(false);
            CDbtn.setSelected(false);
        });
        showBTN.addActionListener(e->{
        });
        panel.add(Box.createHorizontalStrut(20));
        panel.add(lblID);
        panel.add(tfID);
        panel.add(lblTitle);
        panel.add(tfTitle);
        panel.add(lblCollection);
        panel.add(Collection);
        panel.add(lblType);
        panel.add(VCDbtn);
        panel.add(CDbtn);
        panel.add(lblPrice);
        panel.add(tfPrice);
        panel.add(lblYear);
        panel.add(tfYear);
        panel.add(addBTN);
        panel.add(clearBTN);
        panel.add(showBTN);
        add(panel);
    }
}
