package mealplanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SaveCommand implements Command {
    private final Scanner scanner;

    public SaveCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        List<PlanEntry> plan = MealStorage.getPlan();
        if (plan.isEmpty()) {
            System.out.println("Unable to save. Plan your meals first.");
            return false;
        }

        saveToFile(planToIngredients(plan));
        return false;
    }

    private void saveToFile(List<String> ingredients) {
        System.out.println("Input a filename:");
        System.out.print(INPUT_PROMPT);
        String filename = scanner.nextLine();

        File file = new File(filename);
        try (PrintWriter writer = new PrintWriter(file)){
            for (String ingredient : ingredients) {
                writer.println(ingredient);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Saved!");
    }

    private List<String> planToIngredients(List<PlanEntry> plan) {
        return plan.stream()
                .flatMap(planEntry -> planEntry.meal().getIngredients().stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> entry.getValue() > 1
                        ? entry.getKey() + " x" + entry.getValue()
                        : entry.getKey())
                .toList();
    }
}
