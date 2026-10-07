package otus.dz.factory;
import otus.dz.animal.Animal;
import otus.dz.animal.Cat;
import otus.dz.animal.Color;
import otus.dz.animal.Dog;
import otus.dz.animal.birds.Duck;
import otus.dz.factory.AnimalType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalFactory {

    public Animal create(AnimalType type) {
        if (type == AnimalType.CAT) {
            return new Cat();
        } else if (type == AnimalType.DOG) {
            return new Dog();
        } else if (type == AnimalType.DUCK) {
            return new Duck();
        }
        return null;
    }

    public Animal createFromResultSet(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        Animal animal = createByTypeName(type);
        if (animal == null) {
            return null;
        }

        animal.setId(rs.getInt("id"));
        animal.setName(rs.getString("name"));
        animal.setAge(rs.getInt("age"));
        animal.setWeight(rs.getDouble("weight"));
        animal.setColor(Color.fromString(rs.getString("color")));

        return animal;
    }

    private Animal createByTypeName(String typeName) {
        return switch (typeName.toLowerCase()) {
            case "cat" -> new Cat();
            case "dog" -> new Dog();
            case "duck" -> new Duck();
            default -> null;
        };
    }
}