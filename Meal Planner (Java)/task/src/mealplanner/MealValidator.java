package mealplanner;

import java.util.Arrays;
import java.util.List;

public class MealValidator {
    private static final String[] VALID_CATEGORIES = {"breakfast", "lunch", "dinner"};

    public boolean isValidCategory(String category) {
        if (!Arrays.asList(VALID_CATEGORIES).contains(category)) {
            System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
            return false;
        }
        return true;
    }

    public boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()
                || !name.matches("[@A-Za-z ]+")) {
            System.out.println("Wrong format. Use letters only!");
            return false;
        }
        return true;
    }

    public boolean areValidIngredients(List<String> ingredients) {
        for (String ingredient : ingredients) {
            if (!isValidName(ingredient))
                return false;
        }
        return true;
    }

    public boolean areValidIngredients(String ingredientsInput) {
        if (ingredientsInput == null || ingredientsInput.isEmpty() || ingredientsInput.isBlank()
                || !ingredientsInput.matches("\\b(\\W?[A-Za-z ]+){1}([,A-Za-z ]+)*\\b")) {
            System.out.println("Wrong format. Use letters only!");
            return false;
        }
        return true;
    }
}
