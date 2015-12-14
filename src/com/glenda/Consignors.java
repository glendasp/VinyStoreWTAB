package com.glenda;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Consignors extends JFrame{
    private JPanel tabConsignorsJPanel;
    private JTextField textFieldName;
    private JTextField textFieldPhoneNumber;
    private JTextField textFieldAddress;
    private JLabel nameLabel;
    private JLabel phoneNumberLabel;
    private JLabel addressLabel;
    private JLabel zipCodeLabel;
    private JTextField textFieldZipCode;
    private JTextField textFieldCity;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private JLabel amountPaidLabel;
    private JButton saveButton;
    private JTextField textFieldAmountPaid;
    private JComboBox comboBoxState;
    private JButton quitButton;
    private JButton AddNewCon;
    private JButton deleteButton;
    private JTable ConsignorTable;
    private JScrollPane JScrollConsignor;


    public Consignors(final ConsignorModel cm) {

        setContentPane(tabConsignorsJPanel);
        //pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setTitle(" Database Consignor");
        DBManager.setup();
        ConsignorTable.setModel(cm);
        //Set up JTable
        ConsignorTable.setGridColor(Color.BLACK);
        //ConsignorTable.setModel(cm);
        JScrollConsignor.setMinimumSize(new Dimension(600,200));
        pack();

        //Event handlers for add, delete and quit buttons
        saveButton.addActionListener
                (new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Get Movie title, make sure it's not blank
                        String textFieldNameText = textFieldName.getText();

                        if (textFieldName == null || textFieldName.equals("")) {
                            JOptionPane.showMessageDialog(rootPane, "Please enter a Name");
                            return;
                        }
                    }
                });


        //Configurando botão SaveNewConsignor para enviar novo cdastro para a DB.
        AddNewCon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DBManager.addConsignor(textFieldName.getText(),textFieldPhoneNumber.getText(),textFieldAddress.getText(),textFieldZipCode.getText(), textFieldCity.getText(), (String) comboBoxState.getSelectedItem(), textFieldAmountPaid.getColumns());//amountPaidLabel()

                //fazer validação antes de imprimir mensagem
                JOptionPane.showMessageDialog(tabConsignorsJPanel, "New consignor was successfully saved!");


                //ResultSet rs = DBManager.Statement.executeQuery(getAllData);
                //DBManager.consignorModel.updateResultSet(rs);
                DBManager.consignorModel.fireTableDataChanged();
                //get the new consignor model (updated one)
                DBManager.loadAllConsignors();
                ConsignorModel cmNew = DBManager.consignorModel;
                //assign it to the table
                ConsignorTable.setModel(cmNew);
                //pack
                pack();
                System.out.println("Row was added - I did it!!!!!!!!YAY :)");
            }


        });

        //Fecha a tela e finaliza o progama - Nada para adicionar aqui ou alterar
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Definindo ação para o botão quit - que ao clicar, fecha a janela
                    System.exit(0);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentRow = ConsignorTable.getSelectedRow();
                // DBManager.consignorModel.fireTableRowsDeleted();
                DBManager.loadAllConsignors();
                ConsignorModel cmNew = DBManager.consignorModel;

                //This is the code you had, I un-commented it
                if (currentRow == -1) {      // -1 means no row is selected. Display error message.
                    JOptionPane.showMessageDialog(rootPane, "Please choose a consignor to delete");
                }
                boolean deleted = ConsignorModel.deleteRow(currentRow);
                if (deleted) {
                    DBManager.loadAllConsignors();

                    //I added this line. It tells the JTable's model to update; then you'll see the consigner being deleted.
                    ((ConsignorModel)ConsignorTable.getModel()).fireTableDataChanged();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Error deleting");
                }
                }
            });
        }


            public JPanel getPanel() {
                return tabConsignorsJPanel;
            }


        }




