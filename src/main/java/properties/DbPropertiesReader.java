package properties;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DbPropertiesReader {

    private final String dbConfigPath = System.getProperty("user.dir") + "/src/main/resources/db.properties";

    public DbConnectorData getDbProperties () throws FileNotFoundException, IOException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(dbConfigPath)){
            properties.load(fileInputStream);
        }
        return new DbConnectorData(properties.getProperty("username"), properties.getProperty("password"), properties.getProperty("url"));
    }
}
