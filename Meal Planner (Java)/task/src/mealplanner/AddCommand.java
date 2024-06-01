package mealplanner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddCommand implements Command{
    private final Scanner scanner;
    private final MealValidator validator = new MealValidator();

    public AddCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean execute() {
        // 1. ask about category
        Meal.Builder builder = new Meal.Builder();

        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
        String mealCategory;
        do {
            System.out.print(INPUT_PROMPT);
            mealCategory = scanner.nextLine();
        } while (!validator.isValidCategory(mealCategory));

        // 2. ask about meal name
        System.out.println("Input the meal's name:");
        String mealName;
        do {
            System.out.print(INPUT_PROMPT);
            mealName = scanner.nextLine();
        } while (!validator.isValidName(mealName));

        // 3. input ingredients
        System.out.println("Input the ingredients:");
        List<String> ingredients;
        String ingredientsInput;
        do {
            System.out.print(INPUT_PROMPT);
            ingredientsInput = scanner.nextLine();
        } while (!validator.areValidIngredients(ingredientsInput));
        ingredients = Arrays.stream(ingredientsInput.split("\\s*,\\s*")).map(String::trim).toList();

        Meal meal = builder.withCategory(mealCategory)
                .withName(mealName)
                .withIngredients(ingredients)
                .build();
        MealStorage.save(meal);

        System.out.println("The meal has been added!");
        return false;
    }
}
