package com.glenda;
import java.sql.*;

/**
 * Created by glendex on 12/9/15.
 */
public class DBManager {


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/Store"; //Connection string – where's the database?
    static final String USER = "root";   //TODO replace with your username
    static final String PASSWORD = "zc4174by";   //TODO replace with your password

    static Statement Statement = null;
    static Connection Connection = null;
    static ResultSet rs = null;

    static ConsignorModel consignorModel = null;



    public static boolean setup() {

        try {//Here we are catching all the exception.
            Class.forName(JDBC_DRIVER);   //Instantiate the driver class
            Connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);//Create a connection to DB
            Statement = Connection.createStatement();
        }catch (ClassNotFoundException cfe){
            System.out.println(cfe);
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        try {
            String createConsignorTable = "CREATE TABLE IF NOT EXISTS Consignor (ConsignorsID int NOT NULL AUTO_INCREMENT," +
                    " Name VARCHAR(45) NOT NULL, TelephoneNum VARCHAR(13)  NULL, Address VARCHAR(45)  NULL, " +
                    "ZipCode VARCHAR(7)  NULL, City VARCHAR(45)  NULL, State VARCHAR(2) NULL, AmountPaid FLOAT, PRIMARY KEY (ConsignorsID))";

            Statement.executeUpdate(createConsignorTable);
            return true;


        //Imprime um mensagem com o erro que foi gerado caso gere erro sqlexception
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return false;
    }

    //Create or recreate a ResultSet containing the whole database, and give it to movieDataModel
    public static boolean loadAllConsignors(){

        try{

            if (rs!=null) {
                rs.close();
            }

            String getAllData = "SELECT * FROM Consignor";
            rs = Statement.executeQuery(getAllData);

            if (consignorModel == null) {
                //If no current movieDataModel, then make one
                consignorModel = new ConsignorModel(rs);
            } else {
                //Or, if one already exists, update its ResultSet
                consignorModel.updateResultSet(rs);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error loading or reloading movies");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }

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

            //// TODO: 12/9/15  ADd more fields

            psInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }


        return false;
    }

    public static void addInventory(String record, String artist){
        try{
            String newInventorySQLString = "INSERT INTO Inventory (RecordTitle, ArtistName) VALUES (?,?)";
            PreparedStatement psInsert = Connection.prepareStatement(newInventorySQLString);
            psInsert.setString(1, record);
            psInsert.setString(2, artist);
           // psInsert.setString(3, data);

            psInsert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

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

}
