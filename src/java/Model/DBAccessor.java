

package Model;

import java.sql.SQLException;
import java.util.List;

/**
 * This is the contract for all low level JDBC classes.
 * @author Teresa Mahoney
 */
public interface DBAccessor {
    // open connection
    public abstract void openConnection(String driverClassName, String url, String username, String password) 
	throws IllegalArgumentException, ClassNotFoundException, SQLException;
    //close connection
    public abstract void closeConnection() throws SQLException;
// retrieve records
    public abstract List retrieveRecords(String sqlString, boolean closeConnection) throws SQLException,
			Exception;
    public abstract int deleteRecords(String sqlString, boolean closeConnection) throws SQLException,
            Exception;
    public abstract boolean insertRecord(String tableName, List colDescriptors, List colValues, boolean closeConnection)
	throws SQLException, Exception;
    public int updateRecords(String tableName, List colDescriptors, List colValues,
			 String whereField, Object whereValue, boolean closeConnection)
			 throws SQLException, Exception;
}
//
