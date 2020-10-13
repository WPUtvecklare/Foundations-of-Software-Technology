/*
 * Date: 2020-09-23
 * File Name: CollectionTest.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_3;

import ab224qr_assign2.exercise_2.ArrayIntList;
import ab224qr_assign2.exercise_2.ArrayIntStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CollectionTest {
    private ArrayIntList listSut;
    private ArrayIntStack stackSut;

    @BeforeEach
    void setUp () {
        this.listSut = new ArrayIntList();
        this.stackSut = new ArrayIntStack();
    }

    @Test
    void shouldAddNegativeNumbers() {
        addToList(-8, -5);

        Assertions.assertEquals(4, this.listSut.size());
    }

    @Test
    void shouldAddPositiveNumbers() {
        addToList(3, 9);

        Assertions.assertEquals(7, this.listSut.size());
    }

    @Test
    void shouldResizeWhenAddingMoreThan8Elements() {
        addToList(5, 13);

        Assertions.assertEquals(9, this.listSut.size());
    }

    @Test
    void shouldAddMaxValueOfIntegerToArray() {
        int max = Integer.MAX_VALUE;
        boolean exceptionWasThrown = false;

        try {
            this.listSut.add(max);
        } catch (IllegalArgumentException e) {
            exceptionWasThrown = true;
        }

        Assertions.assertFalse(exceptionWasThrown);
    }

    @Test
    void shouldAddMinValueOfIntegerToArray() {
        int min = Integer.MIN_VALUE;
        boolean exceptionWasThrown = false;

        try {
            this.listSut.add(min);
        } catch (IllegalArgumentException e) {
            exceptionWasThrown = true;
        }

        Assertions.assertFalse(exceptionWasThrown);
    }

    @Test
    void shouldHandleLargeArray() {
        boolean exceptionWasThrown = false;

        try {
            addToList(0, 250000);
        } catch (Exception e) {
            exceptionWasThrown = true;
        }

        Assertions.assertFalse(exceptionWasThrown);
    }

    @Test
    void shouldReplaceNumberInIndex() {
        addToList(150, 160);
        int input = -333;
        int index = 5;

        this.listSut.addAt(input, index);
        int actual = this.listSut.get(index);

        Assertions.assertEquals(input, actual);
    }

    @Test
    void shouldIncreaseSizeByOne() {
        addToList(1, 5);
        int initialSize = this.listSut.size();
        int input = -99;
        int index = 2;
        this.listSut.addAt(input, index);

        Assertions.assertEquals(initialSize + 1, this.listSut.size());
    }

    @Test
    void shouldResizeWhenAddingAt() {
        addToList(1, 8);
        int initialSize = this.listSut.size();

        int input = 1000;
        int index = 8;

        this.listSut.addAt(input, index);

        Assertions.assertEquals(initialSize + 1, this.listSut.size());
    }
    
    @Test
    void shouldThrowExceptionWhenAddingOutsideOfIndex() {
        addToList(1, 10);
        int input = 3;
        int index = 11;

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.listSut.addAt(input, index);
        });
    }

    @Test
    void shouldRemoveAtIndex() {
        addToList(1, 5);
        int indexToRemove = 0;

        this.listSut.remove(indexToRemove);
        int actualIndexElement = this.listSut.get(indexToRemove);
        int expectedIndexElement = 2;

        Assertions.assertEquals(expectedIndexElement, actualIndexElement);
    }

    @Test
    void shouldThrowExceptionWhenRemovingOutsideOfIndex() {
        addToList(1, 5);
        int indexToRemove = 5;

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.listSut.remove(indexToRemove);
        });
    }

    @Test
    void shouldRemoveAllElementsOfTheArray() {
        addToList(1, 8);
        int size = this.listSut.size();

        for (int i = 0; i < size; i++) {
            this.listSut.remove(0);
        }

        Assertions.assertTrue(this.listSut.isEmpty());
    }

    @Test
    void shouldReturnIntegerAtIndex() {
        addToList(50, 100);

        int expected = 60;
        int actual = this.listSut.get(10);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionIfIndexIsOutOfRange() {
        addToList(1, 2);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.listSut.get(-1);
        });
    }

    @Test
    void shouldReturnNegativeIfIntegerIsNotInArray() {
        addToList(1, 5);
        int number = 20;

        int expected = -1;
        int actual = this.listSut.indexOf(number);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnIndexOfInteger() {
        addToList(-7, 3);
        int number = -4;

        int expected = 3;
        int actual = this.listSut.indexOf(number);

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Helper method to add items to the array in a given range
     * @param min the first value
     * @param max the last value
     */
    private void addToList(int min, int max) {
        for (int i = min; i <= max; i++) {
            this.listSut.add(i);
        }
    }

    @Test
    void shouldPushItems() {
        this.stackSut.push(18);
        this.stackSut.push(999);

        Assertions.assertEquals(2, this.stackSut.size());
    }

    @Test
    void shouldPushItemToEndOfStack() {
        this.stackSut.push(3);
        this.stackSut.push(1);
        this.stackSut.push(10);

        int lastElement = this.stackSut.peek();

        Assertions.assertEquals(10, lastElement);
    }

    @Test
    void shouldResizeWhenPushAndStackExceeds8Elements() {
        addToStack(1, 9);

        Assertions.assertEquals(9, this.stackSut.size());
    }

    @Test
    void shouldThrowExceptionOnPopWhenStackIsEmpty() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.stackSut.pop();
        });
    }

    @Test
    void shouldReturnRemovedItemInStack() {
        addToStack(30,49);

        int removed = this.stackSut.pop();

        Assertions.assertEquals(49, removed);
    }

    @Test
    void shouldRemoveLastItemInStack() {
        addToStack(99, 131);

        this.stackSut.pop();
        int actual = this.stackSut.peek();

        Assertions.assertEquals(130, actual);
    }

    @Test
    void shouldThrowExceptionOnPeekWhenStackIsEmpty() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            this.stackSut.peek();
        });
    }

    @Test
    void shouldReturnLastItemInStack() {
        addToStack(3,6);

        int actual = this.stackSut.peek();

        Assertions.assertEquals(6, actual);
    }

    @Test
    void shouldNotModifyStackUponPeek() {
        addToStack(5,9);

        int sizeBeforePeek = this.stackSut.size();
        this.stackSut.peek();
        int sizeAfterPeek = this.stackSut.size();

        Assertions.assertEquals(sizeBeforePeek, sizeAfterPeek);
    }

    /**
     * Helper method to add items to the stack in a given range
     * @param min the first value
     * @param max the last value
     */
    private void addToStack(int min, int max) {
        for (int i = min; i <= max; i++) {
            this.stackSut.push(i);
        }
    }
}
