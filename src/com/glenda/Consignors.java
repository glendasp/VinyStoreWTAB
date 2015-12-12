package com.glenda;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.UpdatableResultSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * Created by admin on 5/6/15.
 */
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
    private JButton editButton;
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
        saveButton.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {

                                             //Get Movie title, make sure it's not blank
                                             String titleData = textFieldName.getText();

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



                //get the new consigner model (updated one)
                DBManager.loadAllConsignors();
                ConsignorModel cmNew = DBManager.consignorModel;
                //assign it to the table
                ConsignorTable.setModel(cmNew);
                //pack
                pack();


            }


        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Definindo ação para o botão quit - que ao clicar, fecha a janela
                    System.exit(0);
            }
        });
    }



    public  JPanel getPanel(){
        return tabConsignorsJPanel;
    }


    //private void createUIComponents() {
        // TODO: place custom component creation code here
    //}
}
