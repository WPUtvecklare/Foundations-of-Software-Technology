/*
* Date: 2020-09-02
* File Name: ISBN.java
* Author: Adam Bergman
 */

package ab224qr_assign1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class Description: Used for generating the last digit (checksum) of an ISBN-10 number
 * @version 1.0
 * @author Adam Bergman
 */
public class ISBN {

    /**
     * Starting point of the application
     * @param args
     */
    public static void main(String[] args) {
        ISBN isbn = new ISBN();

        int userInput = isbn.getUserInput();
        ArrayList<Integer> digits = isbn.splitIntoDigitsAndAddLeadingZeros(userInput);
        String checksum = isbn.getISBNChecksum(digits);
        String ISBN10 = isbn.convertListToString(digits) + checksum;

        System.out.println("The ISBN-10 number is: " + ISBN10);
    }

    /**
     * Gets a nine-digit number from the user via the console
     * @return the validated input
     */
    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        Pattern nineDigitNumber = Pattern.compile("^[0-9]{9}$"); // Validation of positive integers

        System.out.print("Enter the first 9 digits of an ISBN as integer: ");

        while (!scanner.hasNext(nineDigitNumber)) {
            System.out.print(scanner.next() + " is not valid. Please enter the first 9 digits of an ISBN as integer: ");
        }

        int userInput = scanner.nextInt();
        scanner.close();
        return userInput;
    }

    /**
     * Splits an integer into an array of individual digits
     * @param numbers to be separated
     * @return array of digits
     */
    private ArrayList<Integer> splitIntoDigitsAndAddLeadingZeros(int numbers) {
        ArrayList<Integer> digitsArray = new ArrayList<Integer>();

        do {
            // Adds the last value of the integer to the array
            digitsArray.add(0, numbers % 10);
            numbers /= 10; // Removes the last value
        } while (numbers > 0);

        // Add leading zeros if they were removed while parsing the number as an integer
        while (digitsArray.size() != 9) {
            digitsArray.add(0, 0);
        }

        return digitsArray;
    }

    /**
     * Generates a checksum for a nine-digit ISBN number
     * @param digits an array of digits containing a nine-digit ISBN
     * @return The full ISBN-10 value
     */
    private String getISBNChecksum(ArrayList<Integer> digits) {
        ArrayList<Integer> list = new ArrayList<>(digits);

        // Formula to calculate ISBN:
        // Multiply each digit with its index (+ 1 since it's 0-index)
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) * (i + 1));
        }

        int checksum = getSumOfListItems(list) % 11;

        // Add an X if the checksum is 10, otherwise use value
        if (checksum == 10) {
            return "X";
        } else {
            return String.valueOf(checksum);
        }
   }

    /**
     * Calculates the sum of all items in a list
     * @param list to calculate
     * @return the total sum
     */
   private int getSumOfListItems(ArrayList<Integer> list) {
       return list.stream().mapToInt(Integer::intValue).sum();
   }

    /**
     * Converts a list to a string
     * @param list the list to stringify
     * @return String of the list items combined
     */
   private String convertListToString(ArrayList<Integer> list) {
        return list.stream().map(Integer::toHexString).collect(Collectors.joining());
   }
}
