/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Owner
 */
public class DB_MySql implements DBAccessor {

    private Connection conn;

    public DB_MySql() {
    }

    @Override
    public void openConnection(String driverClassName, String url, String username, String password) throws IllegalArgumentException, ClassNotFoundException, SQLException {
        String msg = "Error: url is null or zero length!";
        if (url == null || url.length() == 0) {
            throw new IllegalArgumentException(msg);
        }
        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;
        Class.forName(driverClassName);
        conn = DriverManager.getConnection(url, username, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }

    @Override
    public List retrieveRecords(String sqlString, boolean closeConnection) throws SQLException, Exception {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        final List list = new ArrayList();
        Map record = null;
      // do this in an excpetion handler so that we can depend on the
        // finally clause to close the connection
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlString);
            metaData = rs.getMetaData();
            final int fields = metaData.getColumnCount();

            while (rs.next()) {
                record = new HashMap();
                for (int i = 1; i <= fields; i++) {
                    try {
                        record.put(metaData.getColumnName(i), rs.getObject(i));
                    } catch (NullPointerException npe) {
                        // no need to do anything... if it fails, just ignore it and continue
                    }
                } // end for
                list.add(record);
            } // end while

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                stmt.close();
                if (closeConnection) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw e;
            } // end try
        } // end finally

        return list;

    }
//    public static void main(String[] args) throws Exception {
//            DB_MySql db = new DB_MySql();
//            db.openConnection("com.mysql.jdbc.Driver", 
//                    "jdbc:mysql://localhost:3306/menu_project", 
//                    "root", "admin");
//            List l1 = new ArrayList();
//            
//            l1.add("menu_item");
//            l1.add("item_price");
//            l1.add("item_img_url");
//            List l2 = new ArrayList();
//            
//            l2.add("new cupcake");
//            l2.add(5.95);
//            l2.add("images/choco.jpg");
//            db.updateRecords("menu", l1, l2, "menu_id", 21, true);
//            
//            //System.out.println(records);
//        }

    @Override
    public int deleteRecords(String sqlString, boolean closeConnection) throws SQLException, Exception {
        Statement stmt = null;
        int count = 0;
        stmt = conn.createStatement();
        count = stmt.executeUpdate(sqlString);

        return count;
    }

    @Override
    public boolean insertRecord(String tableName, List colDescriptors, List colValues, boolean closeConnection) throws SQLException, Exception {
       Statement stmt = null;
       int count = 0;
        stmt = conn.createStatement();
        // build col strings
        String colStr = "";
         for (int x = 0; x < (colDescriptors.size()); x++) {
                    
                    if (x == (colDescriptors.size() - 1)) {
                        colStr = colStr + colDescriptors.get(x);
                    } else {
                        colStr = colStr + colDescriptors.get(x) + ", ";
                    }
                }
         // build col values
         String valStr = "";
         for (int x = 0; x < (colValues.size()); x++) {
                    
                    if (x == (colValues.size() - 1)) {
                        valStr = valStr + "'"+colValues.get(x)+"'";
                    } else {
                        valStr = valStr + "'"+colValues.get(x)+"'" + ", ";
                    }
                }
         // build sql
         StringBuffer sql = new StringBuffer("INSERT INTO ");
         (sql.append(tableName)).append(" (");
         (sql.append(colStr)).append(") VALUES(");
         (sql.append(valStr)).append(")");
         //
         System.out.println(sql);
         count = stmt.executeUpdate(sql.toString());
         System.out.println(count);
        if (count ==1){
            return true;
        }else{
            return false;
        }
        
    }

    @Override
    public int updateRecords(String tableName, List colDescriptors, List colValues, String whereField, Object whereValue, boolean closeConnection) throws SQLException, Exception {
        Statement stmt = null;
       int count = 0;
       Integer id = Integer.valueOf((int) whereValue);
        stmt = conn.createStatement();
        // build col string with values
        String colStr = "";
         for (int x = 0; x < (colDescriptors.size()); x++) {
                    
                    if (x == (colDescriptors.size() - 1)) {
                        colStr = colStr + colDescriptors.get(x) + " = '"+
                                colValues.get(x)+"'";
                    } else {
                        colStr = colStr + colDescriptors.get(x) + " = '"+
                                colValues.get(x)+"'" + ", ";
                    }
                }
        
         
         // build sql
         StringBuffer sql = new StringBuffer("UPDATE ");
         (sql.append(tableName)).append(" SET ");
         (sql.append(colStr)).append(" WHERE ");
         (sql.append(whereField)).append(" = ");
         sql.append(id);
         //
         System.out.println(sql);
         count = stmt.executeUpdate(sql.toString());
         System.out.println(count);
         return count;
    }// end method

}// end class
