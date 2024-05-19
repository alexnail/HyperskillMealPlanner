import java.util.Objects;
import java.util.Scanner;

// Create a generic class to hold an object of any type
class GenericClass<T> {
    // TODO: Declare a private member variable to store the generic object
    private T t;
    // TODO: Create a constructor to initialize the generic object
    public GenericClass(T t) {
        this.t = t;
    }
    // TODO: Implement a generic method to print the string representation of the object
    public void printString() {
        System.out.println("String representation: " + t.toString());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // TODO: Create an instance of the generic class with a String object
        GenericClass<String> stringGenericClass = new GenericClass<>(scanner.next());
        // TODO: Create an instance of the generic class with an Integer object
        GenericClass<Integer> integerGenericClass = new GenericClass<>(scanner.nextInt());
        // TODO: Call the generic method with the String object
        stringGenericClass.printString();
        // TODO: Call the generic method with the Integer object
        integerGenericClass.printString();
    }
}