package mealplanner;

import java.util.List;

public class ShowCommand implements Command{

    public boolean execute() {
        List<Meal> allMeals = MealStorage.getAllMeals();
        if (allMeals.isEmpty()) {
            System.out.println("No meals saved. Add a meal first.");
            return false;
        }
        for (Meal meal : allMeals) {
            System.out.println("Category: " + meal.getCategory());
            System.out.println("Name: " + meal.getName());
            System.out.println("Ingredients:");
            for (String ingredient: meal.getIngredients()) {
                System.out.println(ingredient);
            }
        }
        return false;
    }
}
