package mealplanner;

import java.util.Scanner;

public class CommandPrompt {
    private final Scanner scanner;

    public CommandPrompt(Scanner scanner) {
        this.scanner = scanner;
    }

    public Command getCommand() {
        System.out.println("What would you like to do (add, show, exit)?");
        return switch (scanner.nextLine()) {
            case "add" -> new AddCommand(scanner);
            case "show" -> new ShowCommand(scanner);
            case "exit" -> new ExitCommand();
            default -> () -> false;
        };
    }
}
