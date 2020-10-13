/*
 * Date: 2020-09-04
 * File Name: AgeDifference.java
 * Author: Adam Bergman
 */

package ab224qr_assign1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Class Description: Calculates the difference between two birthdays
 * @version 1.0
 * @author Adam Bergman
 */
public class AgeDifference {

    /**
     * Starting point of the application
     * @param args
     */
    public static void main(String[] args) {
        AgeDifference ag = new AgeDifference();
        Scanner scanner = new Scanner(System.in);

        LocalDate firstBirthday = ag.getBirthday(scanner, "A");
        LocalDate secondBirthday = ag.getBirthday(scanner, "B");

        scanner.close();

        String difference = ag.getAgeDifference("A", firstBirthday, "B", secondBirthday);
        System.out.println(difference);
    }

    /**
     * Prompts the user to enter a birthday for a given person
     * @param sc Scanner
     * @param person The name of the person
     * @return the birthday
     */
    private LocalDate getBirthday(Scanner sc, String person) {
        System.out.print("Enter Person " + person + "'s date of Birth (YYYY-MM-DD): ");

        DateTimeFormatter validDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            try {
                LocalDate birthday = LocalDate.parse(sc.next(), validDate);
                return birthday;
            } catch (Exception e) {
                System.out.print("Invalid date has been entered, please enter again: ");
            }
        }
    }

    /**
     * Compares the difference between two birthdays
     * @param firstName of the first person
     * @param first birthday to compare
     * @param secondName of the second person
     * @param second birthday to compare
     * @return difference between the ages in a user-friendly format
     */
    private String getAgeDifference(String firstName, LocalDate first, String secondName, LocalDate second) {
        long daysBetween = ChronoUnit.DAYS.between(first, second);

        if (daysBetween > 0) {
            return "Person " + firstName + " is older than Person " + secondName + " by " + daysBetween + " days.";
        } else if (daysBetween < 0) {
            return "Person " + secondName + " is older than Person " + firstName + " by " + -daysBetween + " days.";
        } else {
            return "Person " + firstName + " and " + secondName + " have the same age.";
        }
    }
}