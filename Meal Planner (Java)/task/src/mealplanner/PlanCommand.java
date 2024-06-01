package mealplanner;

import java.util.*;

import static java.util.Objects.isNull;

public class PlanCommand implements Command {

    private static final List<String> WEEKDAYS = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    private static final List<String> CATEGORIES = List.of("breakfast", "lunch", "dinner");
    private final Scanner scanner;

    public PlanCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        List<PlanEntry> plan = new ArrayList<>();
        for (String weekday : WEEKDAYS) {
            System.out.println(weekday);
            for (String category : CATEGORIES) {
                List<Meal> meals = new ArrayList<>(MealStorage.getMeals(category));
                meals.sort(Comparator.comparing(Meal::getName));
                for (Meal meal : meals) {
                    System.out.println(meal.getName());
                }
                System.out.printf("Choose the %s for %s from the list above:%n", category, weekday);
                System.out.print(INPUT_PROMPT);
                boolean added = false;
                do {
                    String choice = scanner.nextLine();
                    Meal meal = meals.stream().filter(m -> m.getName().equals(choice)).findFirst().orElse(null);
                    if (isNull(meal)) {
                        System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
                    } else {
                        added = plan.add(new PlanEntry(weekday, category, meal));
                    }
                } while (!added);
            }
            System.out.printf("Yeah! We planned the meals for %s.%n%n", weekday);
        }

        for (String weekday : WEEKDAYS) {
            System.out.println(weekday);
            for (String category : CATEGORIES) {
                Optional<PlanEntry> planEntry = plan.stream()
                        .filter(row -> row.weekday().equals(weekday) && row.category().equals(category))
                        .findFirst();
                planEntry.ifPresent(entry -> System.out.printf("%s: %s%n", capitalize(category), entry.meal().getName()));
            }
        }

        MealStorage.deleteOldPlan();
        MealStorage.savePlan(plan);
        return false;
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
