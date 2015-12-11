package com.glenda;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 5/6/15.
 */
public class Inventory extends JPanel {
    private JPanel tabInventoryPanel;
    private JTextField textFieldRecordTitle;
    private JTextField textFieldArtistName;
    private JTextField textFieldData;
    private JLabel recordTitleLabel;
    private JLabel artistNameLabel;
    private JLabel dateRecievedLabel;
    private JButton quitButton;
    private JComboBox comboBoxLocation;
    private JButton addNewRecordButton;
    private JList list1;


    public Inventory() {

        addNewRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DBManager.addInventory(textFieldRecordTitle.getText(),textFieldArtistName.getText());
                //fazer validação antes de imprimir mensagem

                JOptionPane.showMessageDialog(tabInventoryPanel, "New Inventory added successfully!");


            }


        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public JPanel getPanel() {
        return tabInventoryPanel;
    }
}
