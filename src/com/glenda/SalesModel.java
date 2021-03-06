package com.glenda;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by glendex on 12/14/15.
 */


//Code was copied from MovieRating and edited

public class SalesModel extends AbstractTableModel {

        private int rowCount = 0;
        private int colCount = 0;
        static ResultSet resultSet;


        public SalesModel(ResultSet rs) {
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
            try {
                resultSet.beforeFirst();// leva o cursor para o inicio
                while (resultSet.next()) { // next() method moves the cursor forward one row and returns true if there is another row ahead
                    rowCount++;
                }
                resultSet.beforeFirst();
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
                //System.out.println("get value at, row = " +row);
                resultSet.absolute(row+1);
                Object o = resultSet.getObject(col+1);
                if(o == null) {
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

//        //Delete row, return true if successful, false otherwise
//        public static boolean deleteRow(int row){
//            try {
//                resultSet.absolute(row + 1);
//                resultSet.deleteRow();
//                //Tell table to redraw itself
//                //fireTableDataChanged();
//                return true;
//            }catch (SQLException se) {
//                System.out.println("Delete row error " + se);
//                return false;
//            }
//        }

//        //returns true if successful, false if error occurs
//        public boolean insertRow(String name, int year, int rating) {
//
//            try {
//                //Move to insert row, insert the appropriate data in each column, insert the row, move cursor back to where it was before we started
//                resultSet.moveToInsertRow();
//                resultSet.updateString("Name", name);
//                //resultSet.updateInt("", year);
//                //resultSet.updateInt(RATING_COLUMN, rating);
//                resultSet.insertRow();
//                resultSet.moveToCurrentRow();
//                fireTableDataChanged();
//                return true;
//
//            } catch (SQLException e) {
//                System.out.println("Error adding row");
//                System.out.println(e);
//                return false;
//            }
//
//        }

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
