package otus.dz.factory;

import otus.dz.animal.Animal;
import otus.dz.animal.Cat;
import otus.dz.animal.Dog;
import otus.dz.animal.birds.Duck;

public class AnimalFactory {

    public Animal create (AnimalType type){
        if (type == AnimalType.CAT){
            return new Cat();
        }   else if (type == AnimalType.DOG){
            return new Dog();
        }   else if (type == AnimalType.DUCK) {
            return new Duck();
        }
        return null;
    }
}

