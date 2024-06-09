import javax.naming.ContextNotEmptyException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class CDFrame extends JFrame {
    public static Management management = Management.getInstance();
    public static CDTableModel cdTableModel;

    public CDFrame(){
        init();
    }
    private void init() {
        Management management = new Management();
        cdTableModel = new CDTableModel();

        setTitle("CD Store");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton newBTN = new JButton("New CD");
        JButton backupBTN = new JButton("Backup");
        JButton restoreBTN = new JButton("Restore");
        JButton refreshBTN = new JButton("Refresh");
        JButton deleteBTN = new JButton("Delete");

        cdTableModel = new CDTableModel();

        JComboBox combo1 = new JComboBox(new String[]{"Title", "Collection", "Type", "Price"});
        JTextField txtSearch = new JTextField(20);
        JButton searchBTN = new JButton("Search");

        newBTN.addActionListener(e -> {
            NewCDDialog newCDDialog = new NewCDDialog();
            newCDDialog.setVisible(true);
        });


        deleteBTN.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure to delete this CD ?","Warning",JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                cdTableModel.fillCDTable(management.getCD());
            }
        });

        CDTableModel finalCdTableModel1 = cdTableModel;
        refreshBTN.addActionListener(e -> {
            ArrayList<CD> list = management.getCD();
            finalCdTableModel1.fillCDTable(list);
        });

        backupBTN.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(null);

            if(result == JFileChooser.APPROVE_OPTION){
                String dir = fileChooser.getSelectedFile().getAbsolutePath();
                FileUtils.write(dir, "CD.eiu", management.getCD());
                JOptionPane.showMessageDialog(null,"CD Backup Successful");
            } else {
                JOptionPane.showMessageDialog(null,"CD Backup Failed");
            }
        });

        restoreBTN.addActionListener(e -> {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CD Files", "eiu");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String dir = selectedFile.getParent();
                String name = selectedFile.getName();
                ArrayList<CD> cdList = new ArrayList<>(FileUtils.read(dir, name));
                management.setList(cdList);
                FileUtils.write("C:\\Documents", "CD.eiu", management.getCD());
                cdTableModel.fillCDTable(management.getCD());
                JOptionPane.showMessageDialog(null, "Successfully restored CDLIST");
            } else {
                JOptionPane.showMessageDialog(null, "Restore operation was cancelled.");
            }
        });

        searchBTN.addActionListener(e -> {
            switch (combo1.getSelectedIndex()) {
                case 0:
                    String title = txtSearch.getText();
                    cdTableModel.fillCDTable(management.searchByTitle(title));
                    break;
                case 1:
                    String collection = txtSearch.getText();
                    cdTableModel.fillCDTable(management.searchByCollection(collection));
                    break;
                case 2:
                    String type = txtSearch.getText();
                    cdTableModel.fillCDTable(management.searchByType(type));
                    break;
                case 3:
                    try{
                        Double price = Double.parseDouble(txtSearch.getText());
                        cdTableModel.fillCDTable(management.searchByPrice(price));
                    }catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Price must be a Number", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Please select a valid option!");
            }
        });
        add(panel);
        panel.add(newBTN);
        panel.add(backupBTN);
        panel.add(restoreBTN);
        panel.add(refreshBTN);
        panel.add(deleteBTN);
        panel.add(cdTableModel);
        panel.add(combo1,BorderLayout.SOUTH);
        panel.add(txtSearch,BorderLayout.SOUTH);
        panel.add(searchBTN,BorderLayout.SOUTH);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new CDFrame().setVisible(true);
        });
    }
}