/*
 * Date: 2020-09-09
 * File Name: TimeDemo.java
 * Author: Adam Bergman
 */
package ab224qr_assign1;

import java.util.Scanner;

/**
 * Class Description: Testing the Time class
 * @version 1.0
 * @author Adam Bergman
 */
public class TimeDemo {
    /**
     * Starting point of the application
     * Prompts the user to provide time and performs calculations on it
     * @param args
     */
    public static void main(String[] args) {
        TimeDemo td = new TimeDemo();
        Scanner scanner = new Scanner(System.in);

        try {
            int totalSeconds = td.getTimeSinceMidnight(scanner,"seconds", 86400);

            Time timeA = new Time(totalSeconds);
            td.tickAndPrint("Time A", timeA, 10);

            int hours = td.getTimeSinceMidnight(scanner,"hours", 23);
            int minutes = td.getTimeSinceMidnight(scanner,"minutes", 59);
            int seconds = td.getTimeSinceMidnight(scanner,"seconds", 59);

            Time timeB = new Time(hours, minutes, seconds);
            td.tickAndPrint("Time B", timeB, 10);

            System.out.println("Time A (+ B): " + timeA.addTime(timeB));

            Time timeC = timeA.subtractTime(timeB);

            System.out.println("Time C: " + timeC);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prompts the user to enter time since midnight
     * @param scanner Scanner
     * @param time type of time (seconds/minutes/hours)
     * @param maxTime maximum allowed time value
     * @return time
     */
    private int getTimeSinceMidnight (Scanner scanner, String time, int maxTime) {
        int number;

        System.out.print("Enter " + time + " since midnight: ");
        number = scanner.nextInt();

        while (number < 0 || number > maxTime) {
            System.out.print(number + " is not valid for " + time + ". Please enter again: ");
            number = scanner.nextInt();
        }

        return number;
    }

    /**
     * Increases time by X times and prints the time to the console
     * @param identifier name of time (Time A, Time B etc.)
     * @param time Time object
     * @param amountOfTimes how many times the time should increase
     */
    private void tickAndPrint(String identifier, Time time, int amountOfTimes) {
        for (int i = 0; i < amountOfTimes; i++) {
            time.tick();
            System.out.println(identifier + ": " + time);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
}
