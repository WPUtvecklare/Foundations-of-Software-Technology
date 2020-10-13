/*
 * Date: 2020-09-26
 * File Name: Word.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_5;

/**
 * Class Description: Represents an alphabetical word
 * @version 1.0
 * @author Adam Bergman
 */
public class Word implements Comparable<Word> {
    private String word;

    public Word(String str) {
        setWord(str);
    }

    /**
     * Validate and set word
     * @param w to be set
     */
    private void setWord(String w) {
        final int maxLength = 100;

        if (!w.matches("[\\p{IsAlphabetic}]*") || w.length() > maxLength) {
            throw new IllegalArgumentException();
        }

        word = w;
    }

    public String toString() {
        return word;
    }

    /**
     * Converts the word into a hash code
     * @return the hash code
     */
    public int hashCode() {
        return word.toLowerCase().hashCode();
    }

    /**
     * Checks if two words are equal
     * @param other object
     * @return true if they are equal, else false
     */
    public boolean equals(Object other) {
        if (other instanceof Word) {
            Word o = ((Word) other);
            return hashCode() == o.hashCode();
        }

        return false;
    }

    /**
     * Compare two words lexicographically
     * @param w to compare with
     * @return negative if greater than, zero if equal, positive if less than the current word
     */
    @Override
    public int compareTo(Word w) {
        return word.compareTo(w.toString());
    }
}
