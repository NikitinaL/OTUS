package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnector {
    void executeSqlRequest (String sqlRequest) throws SQLException;
    ResultSet executeWithResult (String sqlRequest) throws SQLException;
    void close() throws SQLException;
}
