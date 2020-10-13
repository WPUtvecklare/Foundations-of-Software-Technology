/*
 * Date: 2020-09-03
 * File Name: SquareRoot.java
 * Author: Adam Bergman
 */

package ab224qr_assign1;

import java.util.Locale;
import java.util.Scanner;

/**
 * Class Description: Calculates the square root from a given number
 * @version 1.0
 * @author Adam Bergman
 */
public class SquareRoot {

    /**
     * The starting point of the application
     * @param args
     */
    public static void main(String[] args) {
        SquareRoot sr = new SquareRoot();
        System.out.println("This program estimate square roots.");

        int numberToCompute = sr.getNumberToCompute();
        String sqrt = sr.computeSqrt(numberToCompute);
        System.out.println("The estimated square root of " + numberToCompute + " is: " + sqrt);

    }

    /**
     * Prompts the user to enter an integer to estimate the square root of
     * @return a number to compute
     */
    private int getNumberToCompute () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer to estimate the square root of: ");

        while (!scanner.hasNextInt()) {
            System.out.print(scanner.next() + " is not an integer. Please try again: ");
        }

        int numberToCompute = scanner.nextInt();
        scanner.close();

        if (numberToCompute < 0) {
            throw new IllegalArgumentException("Calculations can only be made with positive integers.");
        }

        return numberToCompute;
    }

    /**
     * Computes the square root from a given number
     * @param n the number to compute the square root of
     * @return the square root with two decimals
     */
    private String computeSqrt(int n) {
        double currentGuess = n / 2; // Initial guess
        double lastGuess = currentGuess; // Store the last guess

        double percentageDiff = 1; // Initial diff
        double error = 0.0001; // 1% error rate

        while (Math.abs(percentageDiff) > error) {
            double r = n / currentGuess;
            currentGuess = (currentGuess + r) / 2;
            percentageDiff = (currentGuess - lastGuess) / lastGuess;
            lastGuess = currentGuess;

            System.out.println("Current guess: " + currentGuess);
        }

        return String.format(Locale.ENGLISH, "%.2f", currentGuess);
    }
}
