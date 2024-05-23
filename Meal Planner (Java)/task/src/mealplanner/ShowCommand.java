package mealplanner;

public class ShowCommand implements Command{

    public boolean execute() {
        if (MealStorage.get().isEmpty()) {
            System.out.println("No meals saved. Add a meal first.");
            return false;
        }
        for (Meal meal : MealStorage.get()) {
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
