package com.glenda;
import javax.swing.*;
import java.sql.*;

/**
 * Created by glendex on 12/9/15.
 */
public class DBManager {


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/Store"; //Connection string
    static final String USER = "root";   //TODO replace with your username
    static final String PASSWORD = "zc4174by";   //TODO replace with your password

    static Statement Statement = null;
    static Connection Connection = null;
    static ResultSet rs = null;

    static ResultSet inventoryResult = null;
    static Statement invState = null;

    static ResultSet seleResult = null;
    static Statement saleState = null;

    static ConsignorModel consignorModel = null;
    static InventoryModel inventoryModel = null;
    static SalesModel salesModel = null;


    public static boolean setup() {

        try {//Here we are catching all the exception.
            Class.forName(JDBC_DRIVER);   //Instantiate the driver class
            Connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);//Create a connection to DB
            Statement = Connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            invState = Connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException cfe) {
            System.out.println(cfe);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        try {
            String createConsignorTable = "CREATE TABLE IF NOT EXISTS Consignor " +
                    "(ConsignorsID int NOT NULL AUTO_INCREMENT," +
                    "Name VARCHAR(45) NOT NULL, " +
                    "TelephoneNum VARCHAR(13)  NULL, " +
                    "Address VARCHAR(45)  NULL, " +
                    "ZipCode VARCHAR(7)  NULL, " +
                    "City VARCHAR(45)  NULL, " +
                    "State VARCHAR(2) NULL, " +
                    "AmountPaid DECIMAL(6,2), " +
                    "PRIMARY KEY (ConsignorsID))";
            Statement.executeUpdate(createConsignorTable);

            String createInventoryTable = "CREATE TABLE IF NOT EXISTS Inventory " +
                    "(RecordID INT NOT NULL AUTO_INCREMENT, " +
                    "RecordTitle VARCHAR(45) NULL, " +
                    "ArtistNAme VARCHAR(45) NULL, " +
                    "DateRecieved DATE NULL, " +
                    "PRIMARY KEY (RecordID))"; // "Location VARCHAR(10)"+
            Statement.executeUpdate(createInventoryTable);

//            String createSalesTable = "CREATE TABLE IF NOT EXISTS Sales" +
//                    "(Sales INT NOT NULL AUTO_INCREMENT," +
//                    "PriceSold DECIMAL(6,2) NULL,+" +
//                    "Date Date NULL)";
//            Statement.executeUpdate(createSalesTable);


            loadAllInventory();
//            loadAllSold();


            return true;

            //Imprime um mensagem com o erro que foi gerado caso gere erro sqlexception
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return false;
    }

    //Populando o Jbox com nome dos consignors
    public static void loadConsignorsInventoryTab(JComboBox ConsignorsBox) {

        String getConsignor = "SELECT Consignors.Name FROM Consignor";

        try {
            if (rs != null) {
                rs.close();
            }
            rs = Statement.executeQuery(getConsignor);

            while (rs.next()) {
                //System.out.println(rs.getString(0));
                ConsignorsBox.addItem(rs.getObject("Name").toString());
            }
//
//            if (consignorModel == null) {
//                //If no current movieDataModel, then make one
//                consignorModel = new ConsignorModel(rs);
//            } else {
//                DBManager.addConsignor("name", "phone", "ZipCode", "hfc", "fc", "hf", 2);
//                PreparedStatement psInsert = Connection.prepareStatement(getConsignor);
//
//                //Or, if one already exists, update its ResultSet
//                consignorModel.updateResultSet(rs);
//            }

            //return NameConsigonrs;

        } catch (Exception e) {
            System.out.println("Error loading or reloading consignors");
            System.out.println(e);
            e.printStackTrace();
            //return false;
        }

    }


    // **** Get from user and sends to the database ****
    // addConsignor / addInventry / addSales

    public static boolean addConsignor(String name, String phone, String address, String ZipCode, String city, String State, float AmountPaid){

        try {
            String newConsignorSQLString = "INSERT INTO Consignor (Name, TelephoneNum, Address, ZipCode, City, State, AmountPaid) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement psInsert = Connection.prepareStatement(newConsignorSQLString);
            psInsert.setString(1, name);
            psInsert.setString(2, phone);
            psInsert.setString(3, address);
            psInsert.setString(4, ZipCode);
            psInsert.setString(5, city);
            psInsert.setString(6, State);
            psInsert.setFloat(7, AmountPaid);

            psInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }


        return false;
    }

    public static boolean addInventory(String record, String artist, Date date, String selectedItem){

        try{
            String newInventorySQLString = "INSERT INTO Inventory (RecordTitle, ArtistName, DateRecieved) VALUES (?,?, ?)";
            PreparedStatement psInsert = Connection.prepareStatement(newInventorySQLString);
            psInsert.setString(1, record);
            psInsert.setString(2, artist);
            psInsert.setDate(3, date);

            psInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return false;

    }

    public static void addSales(String PriceSold, String DateSold){
        try{
            String newSalesSQLString = "INSERT INTO Inventory (PriceSold, Date) VALUES (?,?)";
            PreparedStatement psInsert = Connection.prepareStatement(newSalesSQLString);
            psInsert.setString(1, PriceSold);
            psInsert.setString(2, DateSold);
            psInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    // ****  Create or recreate a ResultSet containing the whole database
    // loadAllConsignors / loadAllInventory
    public static boolean loadAllConsignors(){

        try{

            if (rs!=null) {
                rs.close();
            }

            String getAllData = "SELECT * FROM Consignor";

            rs = Statement.executeQuery(getAllData);

            if (consignorModel == null) {
                //If no current consignorModel, then make one
                consignorModel = new ConsignorModel(rs);
            } else {
                //Or, if one already exists, update its ResultSet
                consignorModel.updateResultSet(rs);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error loading or reloading consignors");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }


    public static boolean loadAllInventory(){

        try{
            if (inventoryResult!=null) {
                inventoryResult.close();
            }

            String getAllData = "SELECT * FROM Inventory";
            inventoryResult = invState.executeQuery(getAllData);

            if (inventoryModel == null) {
                //If no current inventoryModel, then make one
                inventoryModel = new InventoryModel(inventoryResult);
            } else {
                //Or, if one already exists, update its ResultSet
                inventoryModel.updateResultSet(inventoryResult);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error loading or reloading inventory");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }


    public static boolean loadAllSold(){

        try{
            if (seleResult!=null) {
                seleResult.close();
            }

            String getAllData = "SELECT * FROM Sales";
            seleResult = saleState.executeQuery(getAllData);

            if (salesModel == null) {
                //If no current salemodel, then make one
                salesModel = new SalesModel(seleResult);
            } else {
                //Or, if one already exists, update its ResultSet
                salesModel.updateResultSet(seleResult);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error loading or reloading sale table");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }



}
