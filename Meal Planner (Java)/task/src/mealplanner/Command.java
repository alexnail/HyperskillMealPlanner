package mealplanner;

public interface Command {
    String INPUT_PROMPT = "> ";

    boolean execute();
}
