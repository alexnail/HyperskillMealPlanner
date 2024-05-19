import java.util.*;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();
        String dateRegex = "((19|20)\\d{2}[\\/.-](0[1-9]|1[0-2])[\\/.-](0[1-9]|[12][0-9]|3[01]))|((0[1-9]|[12][0-9]|3[01])[\\/.-](0[1-9]|1[0-2])[\\/.-](19|20)\\d{2})";

        System.out.println(date.matches(dateRegex) ? "Yes" : "No");
    }
}