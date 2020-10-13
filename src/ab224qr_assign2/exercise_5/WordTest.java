package ab224qr_assign2.exercise_5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordTest {

    @Test
    void shouldSetWordWithLowercaseChars() {
        String name = "hello";
        Word word = new Word(name);

        Assertions.assertEquals(name, word.toString());
    }

    @Test
    void shouldSetWordWithUppercaseChars() {
        String name = "HELLO";
        Word word = new Word(name);

        Assertions.assertEquals(name, word.toString());
    }

    @Test
    void shouldSetWordWithMixedUpperAndLowerCaseChars() {
        String name = "Hello";
        Word word = new Word(name);

        Assertions.assertEquals(name, word.toString());
    }

    @Test
    void shouldThrowErrWhenWordContainsNumbers() {
        String name = "asd3a";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Word word = new Word(name);
        });
    }

    @Test
    void shouldThrowErrWhenWordContainsSpecialChars() {
        String name = "iieqj‰asd!";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Word word = new Word(name);
        });
    }

    @Test
    void shouldThrowErrWhenWordIsLongerThan100Chars() {
        String name = "jqwopeiwqeieufidsfdjsfhdsnjkfsjhfjsdhfdsflaskdjasldkjaksdjasdewqewdyqwtyueqwteyuasdxcxcscassqwedsadas";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Word word = new Word(name);
        });
    }

    @Test
    void shouldAcceptNonEnglishLetters() {
        String name = "AwordContainingÅandÄ";

        Word word = new Word(name);

        Assertions.assertEquals(name, word.toString());
    }

    @Test
    void shouldReturnSameHashForSameWordCaseInsensitive() {
        Word mixed = new Word("MyNewWord");
        Word lower = new Word("mynewword");

        Assertions.assertEquals(mixed.hashCode(), lower.hashCode());
    }

    @Test
    void shouldReturnDifferentHashForDifferentWords() {
        Word first = new Word("MyNewWor");
        Word second = new Word("mynewword");

        Assertions.assertNotEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void shouldReturnTrueOnEqualsCaseInsensitive() {
        Word first = new Word("Macbook");
        Word second = new Word("macbook");

        Assertions.assertTrue(first.equals(second));
    }

    @Test
    void shouldReturnFalseOnNotEquals() {
        Word first = new Word("Macboo");
        Word second = new Word("Macbook");

        Assertions.assertFalse(first.equals(second));
    }

    @Test
    void shouldReturnFalseOnEqualsString() {
        Word first = new Word("Macbook");
        String second = "Macbook";

        Assertions.assertFalse(first.equals(second));
    }

    @Test
    void compareTo() {
        Word first = new Word("Doorbell");
        Word second = new Word("House");

        int r = first.compareTo(second);
        Assertions.assertTrue(r != 0);
    }

    @Test
    void compareToWithMixedChars() {
        Word first = new Word("Doorbell");
        Word second = new Word("doorbell");

        int r = first.compareTo(second);

        Assertions.assertTrue(r != 0);
    }

    @Test
    void shouldReturnTrueOnCompareSameChars() {
        Word first = new Word("Doorbell");
        Word second = new Word("Doorbell");

        int r = first.compareTo(second);

        Assertions.assertTrue(r == 0);
    }

    @Test
    void compareToB() {
        Word first = new Word("Doorbell");
        Word second = new Word("doorbellsinhouse");

        int r = first.compareTo(second);

        Assertions.assertTrue(r != 0);
    }
}