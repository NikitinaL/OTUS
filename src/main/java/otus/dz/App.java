package otus.dz;
import otus.dz.factory.AnimalFactory;
import otus.dz.animal.Animal;
import otus.dz.animal.Color;
import otus.dz.factory.AnimalType;
import tables.AnimalsTable;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static AnimalsTable animalsTable;

    public static void main(String[] args) throws SQLException {
        animalsTable = new AnimalsTable();
        animalsTable.createAnimalsTable();

        List<Animal> animals = loadAnimalsFromDb();
        AnimalFactory factory = new AnimalFactory();

        Commands currentCommand;
        do {
            currentCommand = askForCommand();
            if (currentCommand == Commands.LIST) {
                if (animals.isEmpty()) {
                    System.out.println("Список пуст");
                }
                for (Animal animal : animals) {
                    System.out.println(animal);
                }
            } else if (currentCommand == Commands.ADD) {
                AnimalType animalType = askForAnimalType();
                Animal animal = factory.create(animalType);
                animal.setName(askForName());
                animal.setAge(askForAge());
                animal.setWeight(askForWeight());
                animal.setColor(askForColor());
                animals.add(animal);
                animal.say();

            }
        } while (currentCommand != Commands.EXIT);

    }

    private static List<Animal> loadAnimalsFromDb() throws SQLException {
        return animalsTable.getAllAnimals();
    }

    private static Animal selectAnimalForEdit(List<Animal> animals) {
        if (animals.isEmpty()) {
            System.out.println("Список пуст, нечего редактировать");
            return null;
        }
        System.out.println("Выберите животное для редактирования:");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println((i + 1) + ". " + animals.get(i).getName());
        }
        int choice = scanner.nextInt();
        if (choice < 1 || choice > animals.size()) {
            System.out.println("Неверный выбор");
            return null;
        }
        return animals.get(choice - 1);
    }

    private static void editAnimal(Animal animal) {
        System.out.println("Что изменить?");
        System.out.println("1. Имя");
        System.out.println("2. Возраст");
        System.out.println("3. Вес");
        System.out.println("4. Цвет");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> animal.setName(askForName());
            case 2 -> animal.setAge(askForAge());
            case 3 -> animal.setWeight(askForWeight());
            case 4 -> animal.setColor(askForColor());
            default -> System.out.println("Неверный выбор");
        }
    }

    private static Commands askForCommand() {
        String input = null;
        do {
            if (input != null) {
                System.out.println("Введена неверная команда, попробуйте еще раз");
            }
            System.out.printf("Введите одну из команд (%s):", String.join("/", Commands.VALUES));
            input = scanner.next();
        } while (Commands.doesNotContain(input));
        return Commands.fromString(input);
    }

    private static AnimalType askForAnimalType() {
        String input = null;
        do {
            if (input != null) {
                System.out.println("Введен неверный тип животного, попробуйте еще раз");
            }
            System.out.printf("Введите тип животного (%s):", String.join("/", AnimalType.VALUES));
            input = scanner.next();
        } while (AnimalType.doesNotContain(input));
        return AnimalType.fromString(input);
    }

    private static int askForAge() {
        int input = 0;
        do {
            System.out.println("Введите возраст животного");
            try {
                input = Integer.parseInt(scanner.next());
                if (input <= 0) {
                    System.out.println("Введено не верное значение, попробуйте еще раз");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ввод не является числом, попробуйте еще раз");
            }
        } while (input <= 0);
        return input;
    }

    private static int askForWeight() {
        int input = 0;
        do {
            System.out.println("Введите вес животного");
            try {
                input = Integer.parseInt(scanner.next());
                if (input <= 0) {
                    System.out.println("Введено не верное значение, попробуйте еще раз");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ввод не является числом, попробуйте еще раз");
            }
        } while (input <= 0);
        return input;
    }

    private static String askForName() {
        String input = null;
        do {
            System.out.println("Введите имя животного");
            input = scanner.next();
        } while (input == null);
        return input;
    }

    private static Color askForColor() {
        String input = null;
        do {
            if (input != null) {
                System.out.println("Введен не верный цвет, попробуйте еще раз");
            }
            System.out.printf("Введите один из цветов (%s):", String.join("/", Color.VALUES));
            input = scanner.next();
        } while (Color.doesNotContain(input));
        return Color.fromString(input);
    }

}


