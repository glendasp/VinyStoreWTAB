package com.glenda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton save;
    private JButton editButton;
    private JButton deleteButton;
    private JTable ConsignorTable;


    public Consignors(final ConsignorModel cm) throws Exception{
        //setContentPane(tabConsignorsJPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        DBManager.setup();

        ConsignorTable.setModel(cm);


        //Configurando botão SaveNewConsignor para enviar novo cdastro para a DB.
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DBManager.addConsignor(textFieldName.getText(),textFieldPhoneNumber.getText(),textFieldAddress.getText(),textFieldZipCode.getText(), textFieldCity.getText(), (String) comboBoxState.getSelectedItem(), textFieldAmountPaid.getColumns());//amountPaidLabel()

                //fazer validação antes de imprimir mensagem
                JOptionPane.showMessageDialog(tabConsignorsJPanel, "New consignor was successfully saved!");


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


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
