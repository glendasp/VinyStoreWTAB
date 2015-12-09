package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    private JTextField textFieldState;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private JLabel amountPaidLabel;
    private JButton saveButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JLabel locationLabel;
    private JButton quitButton;
    private JButton saveNewConsignorButton;


    public Consignors() throws Exception{
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //Configure the driver needed
        final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/Store"; //Connection string – where's the database?

        final String USER = "root";   //TODO replace with your username
        final String PASSWORD = "zc4174by";   //TODO replace with your password


        Class.forName(JDBC_DRIVER);   //Instantiate the driver class

        final Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);  //Create a connection to DB
        final Statement statement = connection.createStatement();
        //setContentPane(tabConsignorsJPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Configurando bota SaveNewConsignor para enviar novo cdastro para a DB.
        saveNewConsignorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String createConsignorTable = "CREATE TABLE IF NOT EXISTS Consignor (ConsignorsID int NOT NULL AUTO_INCREMENT," +
                            " Name VARCHAR(45) NOT NULL, TelephoneNum VARCHAR(13)  NULL, Address VARCHAR(45)  NULL, " +
                            "ZipCode VARCHAR(7)  NULL, City VARCHAR(45)  NULL, State VARCHAR(2) NULL, PRIMARY KEY (ConsignorsID))";

                    statement.executeUpdate(createConsignorTable);
                    String consignorName = textFieldName.getText();
                    String newConsignorSQLString = "INSERT INTO Consignor (Name) VALUES (?)";
                    PreparedStatement psInsert = connection.prepareStatement(newConsignorSQLString);
                    psInsert.setString(1, consignorName);
                    psInsert.executeUpdate();
                    connection.close();

                    //System.out.println("Your new consignor was successfully saved! ");
                    JOptionPane.showMessageDialog(tabConsignorsJPanel, "Your new consignor was successfully saved!");

                }catch (SQLException sqle){
                    System.out.println(sqle.getMessage());
                }
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
