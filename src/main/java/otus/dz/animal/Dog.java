package otus.dz.animal;

public class Dog extends Animal{
    @Override
    public void say() {
        System.out.println("Гав");
    }

    @Override
    public void go() {
        System.out.println("Гулять");
    }

    @Override
    public void drink() {
        System.out.println("воду");
    }

    @Override
    public void eat() {
        System.out.println("Вкусняшки");
    }
}
