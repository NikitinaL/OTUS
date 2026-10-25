package tables;

import db.DBConnector;
import db.IDBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbsBaseTable {

    protected IDBConnector dbConnector = null;
    protected String tableName = "";

    public AbsBaseTable(String tableName) {
        this.tableName = tableName;
        try {
            dbConnector = new DBConnector();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка подключения", e);
        }
    }

    public void create(String sqlColumns) throws SQLException {
        String request = String.format("CREATE TABLE IF NOT EXISTS %s (%s);", tableName, sqlColumns);
        dbConnector.executeSqlRequest(request);
    }

    public void delete() throws SQLException {
        dbConnector.executeSqlRequest(String.format("drop table %s;", tableName));
    }

    public ResultSet list() throws SQLException {
        try {
            return dbConnector.executeWithResult(String.format("SELECT * FROM %s;", tableName));
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage(), e);
        }
    }

    public void add() {
    }

    public abstract void insert(Object entity) throws SQLException;

    public abstract void update(Object entity) throws SQLException;

    public abstract ResultSet findByType(String type) throws SQLException;
}
