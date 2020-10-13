/*
 * Date: 2020-09-09
 * File Name: Arrays.java
 * Author: Adam Bergman
 */

package ab224qr_assign1;

/**
 * Class Description: Basic array methods
 * @version 1.0
 * @author Adam Bergman
 */
public class Arrays {

    /**
     * Calculates the average of all integers in the array
     * @param arr the array to calculate mean of
     * @return the mean value
     */
    public static int average(int[] arr) {
        checkIfArrayIsEmpty(arr);

        int total = 0;

        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        return total / arr.length;
    }

    /**
     * Finds the largest value in an array of integers
     * @param arr the array to find the largest value of
     * @return the largest value
     */
    public static int max(int[] arr) {
        checkIfArrayIsEmpty(arr);

        int[] array = sort(arr);
        return array[array.length - 1];
    }

    /**
     * Increases the values of all integers in an array by X
     * @param arr the array to increase the values of
     * @param n the number to increase each value with
     * @return an array containing the added values
     */
    public static int[] addN(int[] arr, int n) {
        checkIfArrayIsEmpty(arr);

        int[] added = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            added[i] = arr[i] + n;
        }

        return added;
    }

    /**
     * Reverse the order of an array
     * @param arr the array to reverse
     * @return an array in reversed order
     */
    public static int[] reverse(int[] arr) {
        checkIfArrayIsEmpty(arr);

        int[] array = arr.clone();

        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            int index = array.length - i - 1;
            array[i] = array[index];
            array[index] = temp;
        }

        return array;
    }

    /**
     * Replaces all occurrences in an array
     * @param arr the array to replace occurrences in
     * @param old the value to replace
     * @param nw the new value
     */
    public static void replaceAll(int[] arr, int old, int nw) {
        checkIfArrayIsEmpty(arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == old ? nw : arr[i];
        }
    }

    /**
     * Sorts an array from smallest to largest
     * @param arr the array to sort
     * @return a sorted array
     */
    public static int[] sort(int[] arr) {
        checkIfArrayIsEmpty(arr);

        int[] array = arr.clone();

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * Checks whether an array contains a set of values in one single sequence
     * @param arr the array to check
     * @param sub the substring to look for in the array
     * @return true if the array contains the substring, else false
     */
    public static boolean hasSubString(int[] arr, int[] sub) {
        checkIfArrayIsEmpty(arr);
        checkIfArrayIsEmpty(sub);

        if (arr.length < sub.length) return false;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < sub.length; j++) {
                if (arr[i + j] != sub[j]) {
                    break;
                }

                if (j == sub.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the absolute difference between two arrays
     * @param arr1 first array to compare
     * @param arr2 second array to compare
     * @return the absolute difference
     */
    public static int[] absDif(int[] arr1, int[] arr2) {
        checkIfArrayIsEmpty(arr1);
        checkIfArrayIsEmpty(arr2);

        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("The arrays must have the same size.");
        }

        int[] array = new int[arr1.length];

        for (int i = 0; i < arr1.length; i++) {
            array[i] = Math.abs(arr1[i] - arr2[i]);
        }
        
        return array;
    }

    /**
     * Helper method to present the items in an array as comma separated values
     * @param arr the array to stringify
     * @return all items in the array separated with commas
     */
    public static String toString(int[] arr) {
        checkIfArrayIsEmpty(arr);

        String str = "{";
        for (int i = 0; i < arr.length; i++) {
            if ((i + 1) != arr.length) {
                str += arr[i] + ", ";
            } else {
                str += arr[i];
            }
        }
        str += "}";
        return str;
    }

    /**
     * Checks if the given array is empty and then throws an Exception
     * @param arr the array to check
     */
    private static void checkIfArrayIsEmpty(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException();
        }
    }
}
