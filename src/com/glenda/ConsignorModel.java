package com.glenda;

/**
 * Created by glendex on 12/10/15.
 */

//Code was copied from MovieRating and edited

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ConsignorModel extends AbstractTableModel {

    private int rowCount = 0;
    private int colCount = 0;
    static ResultSet resultSet;


    public ConsignorModel(ResultSet rs) {
        this.resultSet = rs;
        setup();

    }

    public void setup(){

        countRows();

        try{
            colCount = resultSet.getMetaData().getColumnCount();
            System.out.println("We got this far");

        } catch (SQLException se) {
            System.out.println("Error counting columns" + se);
        }

    }


    public void updateResultSet(ResultSet newRS){
        resultSet = newRS;
        System.out.println(newRS);
        setup();
    }


    private void countRows() {
        rowCount = 0;
        try { resultSet.beforeFirst();  //Move cursor to the start...
            // next() method moves the cursor forward one row and returns true if there is another row ahead
            while (resultSet.next()) {
                rowCount++;

            } resultSet.beforeFirst();

        } catch (SQLException se) {
            System.out.println("Error counting rows " + se);
        }

    }
    @Override
    public int getRowCount() {
        countRows();
        //System.out.println(rowCount);
        return rowCount;
    }

    @Override
    public int getColumnCount(){
        return colCount;
    }

    @Override
    public Object getValueAt(int row, int col){
        try{
            //Conta quantidades de linhas
            resultSet.absolute(row+1);
            Object o = resultSet.getObject(col+1);
            //System.out.println(o);
            if(o == null) {  // If one of the fields are empty (null) it will replace for space so that it does not crash.
                return "";
            } else {
                return o.toString();
            }
        }catch (SQLException se) {
            System.out.println(se);
            //se.printStackTrace();
            return se.toString();
        }
    }


    //Delete row, return true if successful, false otherwise
    public static boolean deleteRow(int row){
        try {
            resultSet.absolute(row + 1);
            resultSet.deleteRow();
            //Tell table to redraw itself
            //fireTableDataChanged();
            return true;
        }catch (SQLException se) {
            System.out.println("Delete row error " + se);
            return false;
        }
    }


    @Override
    public String getColumnName(int col){
        //Get from ResultSet metadata, which contains the database column names
        //TODO translate DB column names into something nicer for display, so "YEAR_RELEASED" becomes "Year Released"
        try {
            return resultSet.getMetaData().getColumnName(col + 1);
        } catch (SQLException se) {
            System.out.println("Error fetching column names" + se);
            return "?";
        }
    }
}

