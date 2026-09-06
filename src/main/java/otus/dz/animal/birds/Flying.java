package otus.dz.animal.birds;

public interface Flying {

    public default void fly(){

        System.out.println("Я лечу");
    }
}
