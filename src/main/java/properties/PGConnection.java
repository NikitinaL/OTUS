package properties;

import java.sql.*;

public class PGConnection implements DBConnector {

    private static volatile PGConnection instance;

    private Connection connection;
    private Statement statement;

    private PGConnection() throws SQLException {
        DbConnectorData dbPropertiesReader;
        try {
            dbPropertiesReader = new DbPropertiesReader().getDbProperties();
        } catch (Exception ex) {
            throw new SQLException("Ошибка загрузки настроек: " + ex.getMessage(), ex);
        }

        if (dbPropertiesReader == null) {
            throw new SQLException("Настройки БД не загружены");
        }

        String url = dbPropertiesReader.getUrl();
        connection = DriverManager.getConnection(url,
                dbPropertiesReader.getUsername(),
                dbPropertiesReader.getPassword());
        statement = connection.createStatement();
    }

    public static PGConnection getInstance() throws SQLException {
        if (instance == null) {
            synchronized (PGConnection.class) {
                if (instance == null) {
                    instance = new PGConnection();
                }
            }
        }
        return instance;
    }

    @Override
    public void executeSqlRequest(String sqlRequest) throws SQLException {
        statement.execute(sqlRequest);
    }

    @Override
    public ResultSet executeWithResult(String sqlRequest) throws SQLException {
        return statement.executeQuery(sqlRequest);
    }

    @Override
    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
            statement = null;
        }
        if (connection != null) {
            connection.close();
            connection = null;
        }
        instance = null;
    }

    private Object readResolve() throws SQLException {
        return getInstance();
    }
}
