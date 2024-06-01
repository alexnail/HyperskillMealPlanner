package mealplanner;

import java.util.Arrays;
import java.util.List;

public class MealValidator {
    private static final String[] VALID_CATEGORIES = {"breakfast", "lunch", "dinner"};
    private static final String WRONG_FORMAT_USE_LETTERS_ONLY = "Wrong format. Use letters only!";
    private static final String VALID_NAME_REGEX = "[A-Za-z\\s]+";

    public boolean isValidCategory(String category) {
        if (!Arrays.asList(VALID_CATEGORIES).contains(category)) {
            System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
            return false;
        }
        return true;
    }

    public boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()
                || !name.matches(VALID_NAME_REGEX)) {
            System.out.println("Wrong format. Use letters only!");
            return false;
        }
        return true;
    }

    public boolean areValidIngredients(String ingredientsInput) {
        if (ingredientsInput == null || ingredientsInput.isEmpty() || ingredientsInput.isBlank()
        || ingredientsInput.trim().endsWith(",")) {
            System.out.println(WRONG_FORMAT_USE_LETTERS_ONLY);
            return false;
        }
        List<String> split = Arrays.stream(ingredientsInput.split("\\s*,\\s*")).map(String::trim).toList();
        if (split.stream().anyMatch(s -> s.isEmpty() || s.isBlank())) {
            System.out.println(WRONG_FORMAT_USE_LETTERS_ONLY);
            return false;
        }
        for (String s : split) {
            if (!s.matches(VALID_NAME_REGEX)) {
                System.out.println(WRONG_FORMAT_USE_LETTERS_ONLY);
                return false;
            }
        }
        return true;
    }
}
