package mealplanner;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    CommandPrompt prompt = new CommandPrompt(scanner);

    while (!prompt.getCommand().execute()) {
    }
  }
}