import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String ipString = scanner.next();
        String regex = "\\b(1?\\d{1,2}|2[0-4]\\d|25[0-5])\\b(\\.\\b(1?\\d{1,2}|2[0-4]\\d|25[0-5])\\b){3}";

        System.out.println(ipString.matches(regex) ? "YES" : "NO");
    }
}