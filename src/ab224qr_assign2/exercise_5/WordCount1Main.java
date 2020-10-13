/*
 * Date: 2020-09-27
 * File Name: WordCount1Main.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Class Description: Reads a file and adds each word into lists of sets
 * @version 1.0
 * @author Adam Bergman
 */
public class WordCount1Main {
    /**
     * The starting point of the application
     * @param args not used
     */
    public static void main(String[] args) {
        try {
            File file = new File("words.txt");

            HashSet<Word> hashSet = new HashSet<>();

            // Override the compare-method in Word-class to match case insensitive
            Comparator<Word> comparator = (o1, o2) -> o1.toString().compareToIgnoreCase(o2.toString());
            TreeSet<Word> treeSet = new TreeSet<>(comparator);

            readAndAddTo(file, hashSet, treeSet);

            System.out.println("TreeSet: " + treeSet.size());
            System.out.println("HashSet: " + hashSet.size());

            System.out.println("---\nListing the words in alphabetical order (TreeSet):\n---");

            for (Word word : treeSet) {
                System.out.println(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("words.txt was not found. Make sure to run IdentifyWordsMain.java.");
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the file word-by-word and adds each word to a Hash- and TreeSet
     * @param file to read
     * @param hashSet to add each word to
     * @param treeSet to add each word to
     * @throws FileNotFoundException if file was not found
     */
    private static void readAndAddTo(File file, HashSet<Word> hashSet, TreeSet<Word> treeSet) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            Word word = new Word(scanner.next());
            hashSet.add(word);
            treeSet.add(word);
        }

        scanner.close();
    }
}
