package properties;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbPropertiesReader {

    private final String dbConfigPath = System.getProperty("user.dir") + "/src/main/resources/db.properties";

    public DbConnectorData getDbProperties() {
        Properties properties = new Properties();

        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (is == null) {
                throw new RuntimeException("Файл db.properties не найден в classpath");
            }
            properties.load(is);

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения db.properties", e);
        }

        return new DbConnectorData(properties.getProperty("user"), properties.getProperty("password"), properties.getProperty("db_name"));
    }
}