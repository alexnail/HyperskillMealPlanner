package mealplanner;

import java.util.LinkedList;
import java.util.List;

public class MealStorage {

    private static List<Meal> storage = new LinkedList<>();

    public static List<Meal> get() {
        return storage;
    }
}
