import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class ConcatPositiveNumbersProblem {

    public static ArrayList<Integer> concatPositiveNumbers(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        ArrayList<Integer> list = new ArrayList<>(l1.stream().filter(i -> i > 0).toList());
        list.addAll(l2.stream().filter(i -> i > 0).toList());
        return list; // write your code here
    }

    /* Do not modify this method */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> list1 = readArrayList(scanner);
        ArrayList<Integer> list2 = readArrayList(scanner);

        ArrayList<Integer> result = concatPositiveNumbers(list1, list2);

        result.forEach(n -> System.out.print(n + " "));
    }

    /* Do not modify this method */
    private static ArrayList<Integer> readArrayList(Scanner scanner) {
        return Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}