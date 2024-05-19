package mealplanner;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 1. ask about category
    System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
    System.out.print("> ");
    String mealCategory = scanner.next();
    // 2. ask abouut meal name
    System.out.println("Input the meal's name:");
    System.out.print("> ");
    String mealName = scanner.next();
    // 3. input ingredients
    System.out.println("Input the ingredients:");
    System.out.print("> ");
    scanner.nextLine(); // workaround for not waiting input
    String ingredientsInput = scanner.nextLine();
    String[] ingredients = ingredientsInput.split("\\s*,\\s*");

    // 4. print all the info
    System.out.println("Category: " + mealCategory);
    System.out.println("Name: " + mealName);
    System.out.println("Ingredients:");
    for (String ingredient: ingredients) {
      System.out.println(ingredient);
    }
    System.out.println("The meal has been added!");
  }
}