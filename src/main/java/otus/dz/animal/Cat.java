package otus.dz.animal;

public class Cat extends Animal{

    @Override
    public void say() {
        System.out.println("Мяу");
    }

    @Override
    public void go() {
        System.out.println("на кухню");
    }

    @Override
    public void drink() {
        System.out.println("молоко");
    }

    @Override
    public void eat() {
        System.out.println("домашнюю еду");
    }
}
