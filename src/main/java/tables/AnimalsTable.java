package tables;

import otus.dz.animal.Animal;
import otus.dz.factory.AnimalFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AnimalsTable extends AbsBaseTable {

    private final AnimalFactory factory = new AnimalFactory();

    public AnimalsTable() {
        super("animals");
    }

    public void createAnimalsTable() throws SQLException {
        super.create("id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(150), " +
                "type VARCHAR(20), " +
                "age INT, " +
                "weight DOUBLE, " +
                "color VARCHAR(50)");
    }

    public void deleteAnimalsTable() {
        try {
            this.delete();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insert(Object entity) throws SQLException {
        Animal animal = (Animal) entity;
        String name = animal.getName().replace("'", "''");
        String colorStr = animal.getColor() != null ? animal.getColor().getValue() : "неизвестный";
        String sql = String.format(Locale.US,
                "INSERT INTO %s (name, type, age, weight, color) VALUES ('%s', '%s', %d, %.2f, '%s');",
                tableName,
                name,
                animal.getClass().getSimpleName(),
                animal.getAge(),
                animal.getWeight(),
                colorStr
        );
        dbConnector.executeSqlRequest(sql);

        ResultSet rs = dbConnector.executeWithResult("SELECT LAST_INSERT_ID();");
        if (rs.next()) {
            animal.setId(rs.getInt(1));
        }
        rs.close();
    }

    @Override
    public void update(Object entity) throws SQLException {
        Animal animal = (Animal) entity;
        if (animal.getId() == 0) {
            throw new SQLException("Невозможно обновить: id не задан");
        }
        String name = animal.getName().replace("'", "''");
        String colorStr = animal.getColor() != null ? animal.getColor().getValue() : "неизвестный";
        String sql = String.format(Locale.US,
                "UPDATE %s SET name='%s', age=%d, weight=%.2f, color='%s' WHERE id=%d;",
                tableName,
                name,
                animal.getAge(),
                animal.getWeight(),
                colorStr,
                animal.getId()
        );
        dbConnector.executeSqlRequest(sql);
    }

    @Override
    public ResultSet findByType(String type) throws SQLException {
        String safeType = type.replace("'", "");
        String sql = String.format("SELECT * FROM %s WHERE type='%s';", tableName, safeType);
        return dbConnector.executeWithResult(sql);
    }

    public List<Animal> getAllAnimals() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        ResultSet rs = list();
        while (rs.next()) {
            Animal animal = factory.createFromResultSet(rs);
            if (animal != null) {
                animals.add(animal);
            }
        }
        rs.close();
        return animals;
    }

    public List<Animal> getAnimalsByType(String type) throws SQLException {
        List<Animal> animals = new ArrayList<>();
        ResultSet rs = findByType(type);
        while (rs.next()) {
            Animal animal = factory.createFromResultSet(rs);
            if (animal != null) {
                animals.add(animal);
            }
        }
        rs.close();
        return animals;
    }
}