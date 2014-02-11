

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
}
//
