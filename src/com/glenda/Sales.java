package com.glenda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by glendex on 12/8/15.
 */
public class Sales extends JFrame{

//Configurando a tela para controle de vendas

    private JTextField textFieldPriceSold;
    private JPanel tabSalesPanel;
    private JTextField textFieldDateSold;
    private JButton quitButton;
    private JButton SoldButton;
    private JList listRecord;
    private JLabel SelectRecord;
    private JLabel SaleDate;
    private JLabel PriceSold;

    public Sales() {
        SoldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.addSales(textFieldDateSold.getText(), textFieldPriceSold.getText());
                JOptionPane.showMessageDialog(tabSalesPanel,"Record Sold");
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
        return tabSalesPanel;
    }

}


