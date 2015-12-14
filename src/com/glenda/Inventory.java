package com.glenda;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Inventory extends JFrame {
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
    private JTable InventoryTable;
    private JScrollPane JScrollInventory;


    public Inventory(final InventoryModel im) {

        setContentPane(tabInventoryPanel);
        //pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setTitle(" Database Inventory");
        DBManager.setup();
//        InventoryTable.setModel(im);
        //Set up JTable
        InventoryTable.setGridColor(Color.BLACK);
        //ConsignorTable.setModel(cm);
        JScrollInventory.setMinimumSize(new Dimension(600,200));
        pack();



        addNewRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DBManager.addInventory(textFieldRecordTitle.getText(),textFieldArtistName.getText());
                //fazer validação antes de imprimir mensagem

                JOptionPane.showMessageDialog(tabInventoryPanel, "New Inventory added successfully!");


            }


        });//Finaliza o programa
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
