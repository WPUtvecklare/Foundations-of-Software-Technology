/*
 * Date: 2020-09-03
 * File Name: ArmStrongNumber.java
 * Author: Adam Bergman
 */

package ab224qr_assign1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Description: Finds armstrong numbers between a given range
 * @version 1.0
 * @author Adam Bergman
 */
public class ArmStrongNumber {

    /**
     * The starting point of the application
     * @param args
     */
    public static void main(String[] args) {
        ArmStrongNumber asn = new ArmStrongNumber();
        Scanner scanner = new Scanner(System.in);
        char repeat;

        do {
            int startingNumber = asn.getStartingNumber(scanner);
            int endingNumber = asn.getEndingNumber(scanner, startingNumber);
            ArrayList armStrongNumbers = asn.getArmStrongNumbersInRange(startingNumber, endingNumber);

            System.out.println("The Armstrong numbers between the given range are:");
            armStrongNumbers.forEach(System.out::println);

            System.out.println("Do you want to repeat? (Y/N): ");
            repeat = scanner.next().charAt(0);
        } while (repeat == 'Y' || repeat == 'y');

        scanner.close();
    }

    /**
     * Gets the starting number from the console
     * @param scanner Scanner
     * @return the starting number
     */
    private int getStartingNumber (Scanner scanner) {
        System.out.print("Enter the starting number of the range: ");

        while (!scanner.hasNextInt()) {
            System.out.println(scanner.next() + " is not valid. Enter starting number of the range: ");
        }

        int startingNumber = scanner.nextInt();
        return startingNumber;
    }

    /**
     * Gets the ending number from the console and
     * validates that it is larger than the starting number
     * @param scanner Scanner
     * @param startingNumber the starting number
     * @return the ending number
     */
    private int getEndingNumber (Scanner scanner, int startingNumber) {
        int endingNumber;

        do {
            System.out.print("Enter the ending number of the range: ");

            while (true) {
                try {
                    endingNumber = Integer.parseInt(scanner.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("The ending number must be an integer that is larger than the starting number. Try again: ");
                }
            }
        } while (endingNumber < startingNumber);

        return endingNumber;
    }

    /**
     * Finds armstrong numbers within a specific range
     * @param startingNumber the starting number
     * @param endingNumber the ending number
     */
    private ArrayList<Integer> getArmStrongNumbersInRange(int startingNumber, int endingNumber) {
        ArrayList<Integer> armStrongNumbers = new ArrayList();

        for (int i = startingNumber; i < endingNumber; i++) {
            int number = i;
            int remainder = 0;
            int sum = 0;
            int amountOfDigits = getAmountOfDigits(number);

            while (number > 0) {
                remainder = number % 10;
                sum += (int)Math.pow(remainder, amountOfDigits);
                number = number / 10;
            }

            if (sum == i) {
                armStrongNumbers.add(i);
            }
        }
        return armStrongNumbers;
    }

    /**
     * Calculates the amount of digits in an integer
     * @param number to be calculated
     * @return the amount of digits
     */
    private int getAmountOfDigits(int number) {
        return (int)Math.log10(number) + 1;
    }
}
