import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String password = scanner.nextLine();

        String upperCaseRegex = ".*[A-Z]+.*";
        String lowerCaseRegex = ".*[a-z]+.*";
        String digitRegex = ".*\\d+.*";
        String lengthRegex = "\\b\\w{12,}\\b";

        boolean isHardToCrack = password.matches(upperCaseRegex)
                && password.matches(lowerCaseRegex)
                && password.matches(digitRegex)
                && password.matches(lengthRegex);
        System.out.println(isHardToCrack ? " YES" : "NO");
    }
}