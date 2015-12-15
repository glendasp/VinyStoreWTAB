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
    private JTable SaleTable; //Jtable that contains all Records for sell
    private JScrollPane JSrollSeles;
    private JTextField textFieldPayConsignor;
    private JTextField textFieldImade;
    private JTable tableSold;
    private JScrollPane JScrollSold;

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
        JSrollSeles.setMinimumSize(new Dimension(100,100));

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



        //Got help with classmate Jessy and Andre from the LC

        //Calculates how much I own consignor and how much I make per record sold.
        SoldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // store the user input from the textBox for sold Price in a soldPriceData variable
                // if the price is less than zero, it will display a dialog message to enter a album's sold price
                double soldPriceData = Double.parseDouble(textFieldPriceSold.getText());

                if (soldPriceData < 0) {
                    JOptionPane.showMessageDialog(rootPane, "Please enter Album's sold price ");
                    return;
                }
                //claculates how much I own consignor
                double payConsignorData = (soldPriceData / 100) * 40;
                textFieldPayConsignor.setText(Double.toString(payConsignorData));


                //Calculates how much I made
                double payOwnerData = (soldPriceData / 100) * 60;
                textFieldImade.setText(Double.toString(payOwnerData));



//                // display a message for adding a data to sales Table
//                System.out.println("Adding " + soldPriceData + " "
//                        + payConsignorData + " " + payOwnerData);


                // if all the user input for the data is usable to insert a row in a salesTable variable stm,
                // it will make the boolean variable of " insertRow" to true and loadAllSales method is called in
                // a MusicDataBase class to fill the data or update the data to a Sales Table result set
//                boolean insertedRow = sm.insertSales(soldPriceData, payConsignorData, payOwnerData);
//                if (insertedRow) {
//
//                    DBManager.loadAllSales();
//                } else {
//                    JOptionPane.showMessageDialog(rootPane, "Error adding a new information");
//                }

            }

        });


        //populating table with records
        JSrollSeles.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int currentRow = SaleTable.getSelectedRow();
                //DBManager.inventoryModel.fireTableRowsDeleted();
                DBManager.loadAllInventory();
                InventoryModel imNew = DBManager.inventoryModel;
                //assign it to the table
                SaleTable.setModel(imNew);
                pack();
            }
        });

        JScrollSold.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int currentRow = SaleTable.getSelectedRow();
               // DBManager.salesModel.fireTableCellUpdated(l);
                DBManager.loadAllSold();
                SalesModel sellNew = DBManager.salesModel;
                //assign it to the table
                SaleTable.setModel(sellNew);
                pack();
            }
        });
    }



    public JPanel getPanel() {
        return tabSalesPanel;
    }

}


