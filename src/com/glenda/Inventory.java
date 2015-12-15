package com.glenda;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;


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
    private JButton deleteButton;
    private JButton editButton;
    private JComboBox comboBoxSelectConsignor;


    public Inventory(final InventoryModel im) {

        setContentPane(tabInventoryPanel);
        //pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        //setTitle(" Database Inventory");
        DBManager.setup();
        InventoryTable.setModel(im); // I don't know why I have to comment this line to run
        //Set up JTable
        InventoryTable.setGridColor(Color.BLACK);
        JScrollInventory.setMinimumSize(new Dimension(300,300));
        pack();


        addNewRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                DBManager.addInventory(textFieldRecordTitle.getText(),textFieldArtistName.getText(),
                        Date.valueOf(textFieldData.getText()), comboBoxLocation.getSelectedItem().toString());

                //fazer validação antes de imprimir mensagem <-- Falta fazer
                JOptionPane.showMessageDialog(tabInventoryPanel, "New Inventory added successfully!");
                //ResultSet rs = DBManager.Statement.executeQuery(getAllData);
                //DBManager.consignorModel.updateResultSet(rs);
                DBManager.inventoryModel.fireTableDataChanged();
                //get the new consignor model (updated one)
                DBManager.loadAllInventory();
                InventoryModel imNew = DBManager.inventoryModel;
                //assign it to the table
                InventoryTable.setModel(imNew);
                //pack
                pack();
                System.out.println("Row was added - I did it!!!!!!!!YAY :)");
            }


        });//Finaliza o programa
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Usuario seleciona um item para ser deletado.
        //Define ação para deletar um record
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int currentRow = InventoryTable.getSelectedRow();
                // DBManager.consignorModel.fireTableRowsDeleted();
                DBManager.loadAllConsignors();
                InventoryModel cmNew = DBManager.inventoryModel;

                //This is the code you had, I un-commented it
                if (currentRow == -1) {      // -1 means no row is selected. Display error message.
                    JOptionPane.showMessageDialog(rootPane, "Please choose a consignor to delete");
                }
                boolean deleted = InventoryModel.deleteRow(currentRow);
                if (deleted) {
                    DBManager.loadAllInventory();

                    //I added this line. It tells the JTable's model to update; then you'll see the consigner being deleted.
                    ((InventoryModel)InventoryTable.getModel()).fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Error deleting");
                }
            }
        });

        //Trying to populate my combobox with consignors name
        comboBoxSelectConsignor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.loadAllInventory();// SELECT Name FROM Store.Inventory

//                comboBoxSelectConsignor.getSelectedItem();
//               // DBManager.loadAllInventory(SELECT C.Name FROM Inventory I join Consignor C on I.RecordID = C.ConsignorsID);
//
//              // for (Object id : KeySet){
//                comboBoxSelectConsignor.addItem(DBManager.loadAllInventory(SELECT Name FROM Store.Inventory));
//              //  }

            }
        });

    }
    public JPanel getPanel() {
        return tabInventoryPanel;
    }
}
