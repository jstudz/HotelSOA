
package hotelsoa;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface DB_Accessor {

    public void closeConnection() throws SQLException;
    
    public void insertRecord(String tableName, List columnNames, List columnValues) throws SQLException;
    
    public void deleteRecord(String tableName, String columnName, String columnValue) throws SQLException;
    
    public void updateRecord(String tableName, List columnsChanged, List valuesChanged, String columnName, Object columnValue) throws SQLException;
    
    public Map findRecordById(String tableName, String primaryKey, Object primaryKeyValue) throws SQLException;

    public void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
    public List findRecords(String sqlStmt) throws SQLException;
}
