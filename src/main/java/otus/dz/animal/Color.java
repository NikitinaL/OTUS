package otus.dz.animal;

import otus.dz.Commands;

import java.util.ArrayList;
import java.util.List;

public enum Color {
    UNDEFINED ("неизвестный"),
    WHITE ("белый"),
    BLACK("черный"),
    GRAY("серый");

    private final String value;

    Color (String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static final List<String> VALUES = collectValues();

    private static List<String> collectValues(){
        List<String> result = new ArrayList<>();
        for (Color color : Color.values()){
            result.add(color.name());
        }
        return result;
    }

    public static boolean doesNotContain (String value){
        return !VALUES.contains(value.toUpperCase().trim());
    }

    public static Color fromString (String value){
        if (value == null){
            return null;
        }
        return Color.valueOf(value.toUpperCase().trim());
    }

}
