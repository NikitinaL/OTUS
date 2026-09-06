package otus.dz;

import otus.dz.factory.AnimalType;

import java.util.ArrayList;
import java.util.List;

public enum Commands {
    ADD,
    LIST,
    EXIT;


    public static final List<String> VALUES = collectValues();

    private static List<String> collectValues(){
        List<String> result = new ArrayList<>();
        for (Commands type : Commands.values()){
            result.add(type.name());
        }
        return result;
    }

    public static boolean doesNotContain (String value){
        return !VALUES.contains(value.toUpperCase().trim());
    }

    public static Commands fromString (String value){
        if (value == null){
            return null;
        }
        return Commands.valueOf(value.toUpperCase().trim());
    }
}

