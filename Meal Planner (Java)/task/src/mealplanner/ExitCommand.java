package mealplanner;

public class ExitCommand implements Command{

    public boolean execute() {
        System.out.println("Bye!");
        return true;
    }
}
