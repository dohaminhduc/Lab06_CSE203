import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewFrame extends JFrame {
    public NewFrame(){
        init();
    }
    private void init(){
        setTitle("This is my First Name");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setSize(400,400);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("Resort Price Calculator");
        title.setFont(new Font("Serif", Font.BOLD, 40));
        JLabel price = new JLabel("Base price for a room is $200");
        price.setFont(new Font("Serif", Font.BOLD, 32));
        JLabel options = new JLabel("Choose options that you want");
        options.setFont(new Font("Serif", Font.BOLD, 32));
        options.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblName = new JLabel("Guest Name: ");
        JTextField tfName = new JTextField(27);

        JCheckBox BreakfastBTN = new JCheckBox("Breakfast $20");
        BreakfastBTN.setAlignmentX(Component.CENTER_ALIGNMENT);
        JCheckBox GoftBTN = new JCheckBox("Goft $50");
        GoftBTN.setAlignmentX(Component.CENTER_ALIGNMENT);
        JCheckBox PoolBTN = new JCheckBox("Pool $15");
        PoolBTN.setAlignmentX(Component.CENTER_ALIGNMENT);
        ButtonGroup group = new ButtonGroup();
        group.add(BreakfastBTN);
        group.add(GoftBTN);
        group.add(PoolBTN);

        JComboBox combo = new JComboBox(new String[] {"Week day (-10%)","Weekend (+30%)"});
        combo.setSize(300,30);
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton CalculateBTN = new JButton("Calculate");
        CalculateBTN.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(price);
        panel.add(options);
        panel.add(lblName);
        panel.add(tfName);
        panel.add(BreakfastBTN);
        panel.add(GoftBTN);
        panel.add(PoolBTN);
        panel.add(combo);
        panel.add(CalculateBTN);

        CalculateBTN.addActionListener(e -> {
            int totalPrice = 0;
            JLabel totalPayment = new JLabel();
            try {
                String name = tfName.getText();
                if (name.length() == 0){
                    JOptionPane.showMessageDialog(null, "Please enter a valid name!");
                }
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Please enter a valid name!");
            }
            if(combo.getSelectedItem().toString().equals("Week day (-10%)")){
                if(BreakfastBTN.isSelected()){
                    totalPrice = 220 - 220 * 10/100;
                } else if(GoftBTN.isSelected()){
                    totalPrice = 250 - 250 * 10/100;
                } else if(PoolBTN.isSelected()){
                    totalPrice = 215 - 215 * 10/100;
                }
            } else {
                if(BreakfastBTN.isSelected()){
                    totalPrice = 220 + 220 * 30/100;
                } else if(GoftBTN.isSelected()){
                    totalPrice = 250 + 250 * 30/100;
                } else if(PoolBTN.isSelected()){
                    totalPrice = 215 + 215 * 30/100;
                }
            }
            totalPayment.setText("Hello " + tfName.getText() + " Your Payment is: "+"$"+totalPrice);
            panel.add(totalPayment);
        });
        add(panel);
    }
        public static void main(String[] args) {
            EventQueue.invokeLater(() -> {
                new NewFrame().setVisible(true);
            });
        }
    }

