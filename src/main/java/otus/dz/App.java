package otus.dz;

import otus.dz.animal.Animal;
import otus.dz.animal.Color;
import otus.dz.animal.birds.Duck;
import otus.dz.animal.birds.Flying;
import otus.dz.factory.AnimalFactory;
import otus.dz.factory.AnimalType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main (String[] args){
        List<Animal> animals = new ArrayList<>();
        AnimalFactory factory = new AnimalFactory();

        Commands currentCommand;
        do {
            currentCommand = askForCommand();
            if (currentCommand == Commands.LIST){
                if (animals.isEmpty()) {
                    System.out.println("Список пуст");
                }
                for (Animal animal : animals){
                    System.out.println(animal);
                }
            } else if (currentCommand == Commands.ADD)  {
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
    private static Commands askForCommand(){
        String input = null;
        do {
            if (input != null) {
                System.out.println("Введена неверная команда, попробуйте еще раз");
            }
            System.out.printf("Введите одну из команд (%s):", String.join("/",Commands.VALUES));
            input = scanner.next();
        } while (Commands.doesNotContain(input));
        return Commands.fromString(input);
    }

    private static AnimalType askForAnimalType(){
        String input = null;
        do {
            if (input != null) {
                System.out.println("Введен неверный тип, попробуйте еще раз");
            }
            System.out.printf("Введите тип животного (%s):", String.join("/",AnimalType.VALUES));
            input = scanner.next();
        } while (AnimalType.doesNotContain(input));
        return AnimalType.fromString(input);
    }

    private static int askForAge (){
        int input = 0;
        do {
            System.out.println("Введите возраст животного");
           try {
               input = Integer.parseInt(scanner.next());
                if (input <= 0){
                System.out.println("Введен неверный возраст, попробуйте еще раз");
                }
            } catch (NumberFormatException e) {
               System.out.println("Ввод не является числом, попробуйте еще раз");
           }
        } while (input <=0 );
        return input;
    }
    private static int askForWeight (){
        int input = 0;
        do {
            System.out.println("Введите вес животного");
            try {
                input = Integer.parseInt(scanner.next());
                if (input <= 0){
                    System.out.println("Введен неверный вес, попробуйте еще раз");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ввод не является числом, попробуйте еще раз");
            }
        } while (input <=0 );
        return input;
    }
    private static String askForName (){
        String input = null;
        do {
            System.out.println("Введите имя животного");
            input = scanner.next();
        }   while (input == null);
        return input;
    }
    private static Color askForColor(){
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


