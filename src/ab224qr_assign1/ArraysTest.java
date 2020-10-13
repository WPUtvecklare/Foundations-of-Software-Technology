package ab224qr_assign1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class ArraysTest {
    private int[] arr = {3,1,2,5,4};

    @AfterEach
    void arrayShouldBeUnchanged() {
        int[] array = {3,1,2,5,4};
        Assertions.assertArrayEquals(array, arr);
    }

    @Test
    void shouldReturn3AsAverage() {
        int expected = 3;
        int actual = Arrays.average(arr);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturn5AsMax() {
        int expected = 5;
        int actual = Arrays.max(arr);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnArrayWithValuesIncreasedByTwo() {
        int[] expected = {5,3,4,7,6};
        int[] actual = Arrays.addN(arr, 2);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnReversedArray() {
        int[] expected = {4,5,2,1,3};
        int[] actual = Arrays.reverse(arr);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReplaceOccurrencesInArray() {
        int[] expected = {4,4,1,10,2};
        int[] array = {4,4,1,5,2};
        Arrays.replaceAll(array, 5, 10);
        Assertions.assertArrayEquals(expected, array);
    }

    @Test
    void shouldReturnSortedArray() {
        int[] expected = {1,3,5,6,7};
        int[] array = {7,1,6,3,5};
        int[] actual = Arrays.sort(array);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnTrueWhenArrayContainsSubString() {
        int[] subString = {1,2,5};
        boolean actual = Arrays.hasSubString(arr, subString);

        Assertions.assertEquals(true, actual);
    }

    @Test
    void shouldReturnFalseWhenArrayDoesNotContainSubString() {
        int[] subString = {1,2,4};
        boolean actual = Arrays.hasSubString(arr, subString);

        Assertions.assertEquals(false, actual);
    }

    @Test
    void shouldThrowExceptionWhenArraysArentSameSize() {
        int[] array = {1,2};
        try {
            Arrays.absDif(arr, array);
            fail("Should have thrown exception");
        } catch (Exception e) {
        }
    }

    @Test
    void shouldReturnAbsDifference() {
        int[] first = {1,2,3,4,5};
        int[] second = {1,1,1,1,1};

        int[] expected = {0,1,2,3,4};
        int[] actual = Arrays.absDif(first, second);
        Assertions.assertArrayEquals(expected, actual);
    }
}