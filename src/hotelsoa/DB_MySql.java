package hotelsoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB_MySql implements DB_Accessor {

    private Connection conn;

    @Override
    public void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }

    public void insertRecord(String tableName, List columnNames, List columnValues) throws SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        final int columnNum = columnNames.size();
        PreparedStatement stmt = null;

        for (int i = 0; i < columnNum; i++) {
            sql.append(columnNames.get(i) + ", ");
        }

        sql = new StringBuilder(sql.toString().substring(0, sql.lastIndexOf(",")) + ") VALUES (");

        for (int j = 0; j < columnNum; j++) {
            sql.append("?, ");
        }
        
        final String finalSqlStmt = sql.toString().substring(0, sql.lastIndexOf(",")) + ")";
        conn.prepareStatement(finalSqlStmt);
        
        final int valueNum = columnValues.size();
        
        for (int k = 0; k < valueNum; k++) {
            Object obj = columnValues.get(k);
            
            if (obj instanceof String) {
                stmt.setString(k + 1, obj.toString());
            } else if (obj instanceof Integer) {
                stmt.setInt(k, (int)obj);
            }
        }
        
        stmt.execute();
        
    }

    public List findRecords(String sqlStmt) throws SQLException {
        final List allRecords = new ArrayList();
        Map record = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sqlStmt);
        metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            record = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                record.put(metaData.getColumnName(i), rs.getObject(i));
            }
            allRecords.add(record);
        }

        return allRecords;
    }

    @Override
    public Map findRecordById(String tableName, String primaryKey, Object primaryKeyValue) throws SQLException {
        final Map record = new HashMap();
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;

        String pkValue = null;

        if (primaryKeyValue instanceof String) {
            pkValue = "= '" + primaryKeyValue + "'";
        } else {
            pkValue = "= " + primaryKeyValue;
        }

        String sql = "SELECT * FROM " + tableName + " WHERE " + primaryKey + pkValue;

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                record.put(metaData.getColumnName(i), rs.getObject(i));
            }
        }

        return record;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DB_MySql db = new DB_MySql();
        Map record = new HashMap();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/hotel_db", "root", "admin");
//        record = db.findRecordById("hotel", "hotel_id", 1);
//        System.out.println(record);
        List records = new ArrayList();
        records = db.findRecords("SELECT * FROM HOTEL where note = 'free wifi'");
        for (int i = 0; i < records.size(); i++) {
            System.out.println(records.get(i));
        }
        
        List colNames = new ArrayList();
        colNames.add("hotel_id");
        colNames.add("hotel_name");
        colNames.add("street_address");
        colNames.add("city");
        colNames.add("state");
        colNames.add("postal_code");
        colNames.add("note");
        List colValues = new ArrayList();
        colValues.add(3);
        colValues.add("The Langham, Chicago");
        colValues.add("330 North Wabash Avenue");
        colValues.add("Chicago");
        colValues.add("IL");
        colValues.add("60611");
        colValues.add("Free Wifi");
        
        db.insertRecord("hotel", colNames, colValues);
        
        db.closeConnection();
    }

}
