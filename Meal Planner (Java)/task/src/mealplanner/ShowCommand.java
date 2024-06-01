package mealplanner;

import java.util.List;
import java.util.Scanner;

public class ShowCommand implements Command{

    private final Scanner scanner;
    private static final List<String> VALID_CATEGORIES = List.of("breakfast", "lunch", "dinner");

    public ShowCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean execute() {
        String category = requestCategory();

        List<Meal> meals = MealStorage.getMeals(category);
        if (meals.isEmpty()) {
            System.out.println("No meals found.");
            return false;
        }

        System.out.println("Category: " + category);

        for (Meal meal : meals) {
            System.out.println("Name: " + meal.getName());
            System.out.println("Ingredients:");
            for (String ingredient: meal.getIngredients()) {
                System.out.println(ingredient);
            }
            System.out.println("");
        }
        return false;
    }

    private String requestCategory() {
        String category;
        do {
            System.out.println("Which category do you want to print (breakfast, lunch, dinner)?");
            System.out.print("> ");
            category = scanner.nextLine();
        } while(!isValidCategory(category));
        return category;
    }

    private boolean isValidCategory(String category) {
        boolean contains = VALID_CATEGORIES.contains(category);
        if (!contains) {
            System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
        }
        return contains;
    }
}
