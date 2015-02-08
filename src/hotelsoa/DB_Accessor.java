
package hotelsoa;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface DB_Accessor {

    void closeConnection() throws SQLException;

    Map findRecordById(String tableName, String primaryKey, Object primaryKeyValue) throws SQLException;

    void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
     public List findRecords(String sqlStmt) throws SQLException;
}
