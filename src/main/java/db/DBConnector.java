package db;

import properties.DbConnectorData;
import properties.DbPropertiesReader;

import java.sql.*;


public class DBConnector implements IDBConnector {

    private static Connection connection = null;
    private static Statement statement = null;

    public DBConnector() throws SQLException {
        DbConnectorData dbPropertiesReader = null;
        try {
            dbPropertiesReader = new DbPropertiesReader().getDbProperties();
        } catch (Exception ex) {
            throw new SQLException("Ошибка загрузки настроек: " + ex.getMessage(), ex);
        }

        if (dbPropertiesReader == null) {
            throw new SQLException("Настройки БД не загружены");
        }

        String url = "jdbc:mysql://127.0.0.1:3306/" + dbPropertiesReader.getDbName();

        if (connection == null) {
            connection = DriverManager.getConnection(url, dbPropertiesReader.getUsername(), dbPropertiesReader.getPassword());
        }

        if (statement == null) {
            statement = connection.createStatement();
        }
    }

    public void executeSqlRequest(String sqlRequest) throws SQLException {
        statement.execute(sqlRequest);
    }

    public ResultSet executeWithResult(String sqlRequest) throws SQLException {
        return statement.executeQuery(sqlRequest);
    }

    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
            statement = null;
        }

        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}




