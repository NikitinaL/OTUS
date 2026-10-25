package properties;

public class DbConnectorData {

    private String username = "";
    private String password = "";
    private String dbName = "";

    public DbConnectorData(String username, String password, String dbName) {
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDbName() {
        return dbName;
    }
}