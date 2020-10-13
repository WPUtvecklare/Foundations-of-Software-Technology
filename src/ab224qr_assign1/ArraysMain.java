/*
 * Date: 2020-09-09
 * File Name: ArraysMain.java
 * Author: Adam Bergman
 */

package ab224qr_assign1;

/**
 * Class Description: Demonstrates methods of Arrays class
 * @version 1.0
 * @author Adam Bergman
 */
public class ArraysMain {

    /**
     * Starting point of the application
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {3,1,7,4,2,10,5,50};
        System.out.println("Demonstrating Array class with: \n" + Arrays.toString(arr) + "\n");

        int average = Arrays.average(arr);
        System.out.println("Average: " + average);
        int max = Arrays.max(arr);
        System.out.println("Max: " + max);
        int[] addedN = Arrays.addN(arr, 2);
        System.out.println("Added number (2): " + Arrays.toString(addedN));
        int[] reversed = Arrays.reverse(arr);
        System.out.println("Reversed: " + Arrays.toString(reversed));
        int[] sorted = Arrays.sort(arr);
        System.out.println("Sorted: " + Arrays.toString(sorted));

        int[] correctSubString = {7,4,2,10};
        boolean hasSubString = Arrays.hasSubString(arr, correctSubString);
        System.out.println("Has Substring " + Arrays.toString(correctSubString) + ": " + hasSubString);
        int[] invalidSubString = {7,4,2,5};
        boolean hasNotSubString = Arrays.hasSubString(arr, invalidSubString);
        System.out.println("Has Substring " + Arrays.toString(invalidSubString) + ": " + hasNotSubString);

        int[] firstArrForAbsDif = {1,2,3,4,5};
        int[] secondArrForAbsDif = {1,1,1,1,1};

        try {
            int[] absDif = Arrays.absDif(firstArrForAbsDif, secondArrForAbsDif);
            System.out.println("Abs difference between {1,2,3,4,5} and {11,12,13,14,15}: " + Arrays.toString(absDif));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Arrays.replaceAll(arr, 2, 9);
        System.out.println("Replaced array (2 with 9): " + Arrays.toString(arr));
    }
}
