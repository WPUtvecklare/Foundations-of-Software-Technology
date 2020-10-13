/*
 * Date: 2020-09-07
 * File Name: Anagram.java
 * Author: Adam Bergman
 */

package ab224qr_assign1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Description: Finds all anagrams for a provided word
 * @version 1.0
 * @author Adam Bergman
 */
public class Anagram {

    /**
     * Starting point of the application
     * @param args
     */
    public static void main(String[] args) {
        Anagram am = new Anagram();
        File file = new File("src/ab224qr_assign1/wordlist");

        if (!file.exists()) {
            file = am.getFile();
        }

        ArrayList<String> wordList = am.readFile(file);

        String word = am.getWord();
        ArrayList<String> anagrams = am.getAnagrams(word, wordList);
        am.presentAnagrams(anagrams);
    }

    /**
     * Get file path via console input
     * @return the file
     */
    private File getFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to file: ");
        String path = scanner.nextLine();
        File file = new File(path);

        while (!file.exists() || file.isDirectory()) {
            System.out.print("Invalid path. Please try again: ");
            path = scanner.nextLine();
            file = new File(path);
        }

        scanner.close();
        return file;
    }

    /**
     * Reads all words in a file
     * @param file to read
     * @return an array of single words
     */
    private ArrayList<String> readFile(File file) {
        ArrayList<String> words = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                words.add(sc.next().toLowerCase());
            }

            sc.close();
            return words;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        return words;
    }

    /**
     * Prompts the user to enter a word
     * @return a word
     */
    private String getWord () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word: ");

        // Validate input as alphabetical
        while (!scanner.hasNext("[A-Za-z]+")) {
            System.out.print("Invalid input. Please enter again: ");
            scanner.next();
        }

        String word = scanner.next().toLowerCase();
        scanner.close();
        return word;
    }

    /**
     * Searches for anagrams in an array of words for the provided word
     * @param word to look for anagrams
     * @param wordList the list of all words
     * @return an array of anagrams
     */
    private ArrayList<String> getAnagrams (String word, ArrayList<String> wordList) {
        ArrayList<String> anagrams = new ArrayList<>();

        for (String w : wordList) {
            boolean isAnagram = isAnagram(word, w);

            // Add to the list if it's an anagram and not a duplicate
            if (isAnagram && !anagrams.contains(w)) {
                anagrams.add(w);
            }
        }

        return anagrams;
    }

    /**
     * Determines whether a word is an anagram
     * @param a the word to look for anagrams
     * @param b the word in the list
     * @return true if an anagram was found, false if not
     */
    private  boolean isAnagram(String a, String b) {
        // Convert each string into an array of separated characters
        char[] first = toLowerCaseCharArray(a);
        char[] second = toLowerCaseCharArray(b);

        // If the length of the compared words aren't the same, it cannot be an anagram
        if (first.length != second.length) {
            return false;
        }

        first = sort(first);
        second = sort(second);

        // Check that the words share the same characters, returns false if not
        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts a string into an array of characters
     * @param toBeConverted
     * @return an array of characters
     */
    private char[] toLowerCaseCharArray(String toBeConverted) {
        return toBeConverted.toLowerCase().toCharArray();
    }

    /**
     * Sorts an array of chars to A-Z order
     * @param arr the array to sort
     * @return a sorted char-array
     */
    public static char[] sort(char[] arr) {
        char[] array = arr.clone();

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    char temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * Prints anagrams
     * @param anagrams an array of words (anagrams)
     */
    private void presentAnagrams (ArrayList<String> anagrams) {
        if (anagrams.size() == 0) {
            System.out.println("No anagrams were found");
        } else {
            System.out.println("Anagrams:");
        }

        for (String w : anagrams) {
            System.out.println(w);
        }
    }
}
