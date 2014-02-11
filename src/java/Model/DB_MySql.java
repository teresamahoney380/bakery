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
public class DB_MySql implements DBAccessor
{
    private Connection conn;

    public DB_MySql() {
    }

    @Override
    public void openConnection(String driverClassName, String url, String username, String password) throws IllegalArgumentException, ClassNotFoundException, SQLException {
        String msg = "Error: url is null or zero length!";
		if( url == null || url.length() == 0 ) throw new IllegalArgumentException(msg);
		username = (username == null) ? "" : username;
		password = (password == null) ? "" : password;
		Class.forName (driverClassName);
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
			final int fields=metaData.getColumnCount();

			while( rs.next() ) {
				record = new HashMap();
				for( int i=1; i <= fields; i++ ) {
					try {
						record.put( metaData.getColumnName(i), rs.getObject(i) );
					} catch(NullPointerException npe) { 
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
				if(closeConnection) conn.close();
			} catch(SQLException e) {
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
//            
//            List records = db.retrieveRecords("select menu_id, menu_item, item_price from menu", true);
//            System.out.println(records);
//        }
    
}// end class
