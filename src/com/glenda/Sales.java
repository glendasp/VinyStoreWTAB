package com.glenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


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
    private JLabel SelectRecord;
    private JLabel SaleDate;
    private JLabel PriceSold;
    private JTable SaleTable;
    private JScrollPane JSrollSeles;

    public Sales(final SalesModel sm) {

        setContentPane(tabSalesPanel);
        //pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setTitle(" Database Inventory");
        DBManager.setup();
//        SaleTable.setModel(sm); // I don't know why I have to comment this line to run
        //Set up JTable
        SaleTable.setGridColor(Color.BLACK);
        JSrollSeles.setMinimumSize(new Dimension(300,300));
        pack();


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

        JSrollSeles.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int currentRow = SaleTable.getSelectedRow();
                // DBManager.consignorModel.fireTableRowsDeleted();
                DBManager.loadAllInventory();
                InventoryModel imNew = DBManager.inventoryModel;
                //assign it to the table
                SaleTable.setModel(imNew);
                pack();
            }
        });
    }



    public JPanel getPanel() {
        return tabSalesPanel;
    }

}


