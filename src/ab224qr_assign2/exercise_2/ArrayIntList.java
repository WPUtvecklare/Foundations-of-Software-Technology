/*
 * Date: 2020-09-23
 * File Name: ArrayIntList.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_2;

import two_dv600.AbstractIntCollection;
import two_dv600.IntList;

import java.util.Iterator;

/**
 * Class Description: Implements methods for working with integer lists
 * @version 1.0
 * @author Adam Bergman
 */
public class ArrayIntList extends AbstractIntCollection implements IntList {
    /**
     * Adds integer to the end of the list
     * @param n the number to add
     */
    @Override
    public void add(int n) {
        resizeIfNeeded();

        values[size++] = n;
    }

    /**
     * Inserts integer at given index position and shifting the following integers to the right
     * @param n the number to replace the current index with
     * @param index the position of the current integer
     * @throws IndexOutOfBoundsException if the given index is outside of the range
     */
    @Override
    public void addAt(int n, int index) throws IndexOutOfBoundsException {
        checkIfOutOfRange(index, size + 1);
        resizeIfNeeded();

        // Copy the existing array, replace it after the position of the index
        // with an array that starts at the index position and increase the size of the array
        System.arraycopy(values, index, values, index + 1, size - index);
        size++;
        values[index] = n;
    }

    /**
     * Removes an integer at given index position
     * @param index the index where the integer to remove is
     * @throws IndexOutOfBoundsException if the given index is outside of the range
     */
    @Override
    public void remove(int index) throws IndexOutOfBoundsException {
        checkIfOutOfRange(index, size);

        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }

        size--;
    }

    /**
     * Get integer at given index position
     * @param index to get the integer from
     * @return the integer
     * @throws IndexOutOfBoundsException if the given index is outside of the range
     */
    @Override
    public int get(int index) throws IndexOutOfBoundsException {
        checkIfOutOfRange(index, size);

        return values[index];
    }

    /**
     * Finds the index position of the given integer
     * @param n the integer to lookup the index of
     * @return the index of the integer, -1 if it wasn't found
     */
    @Override
    public int indexOf(int n) {
        if (!isEmpty()) {
            int i = 0;

            for (Iterator<Integer> itr = iterator(); itr.hasNext(); i++) {
                if (itr.next() == n) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Helper method to check if the given index is within the size
     * @param i the index
     * @param size the size
     * @throws IndexOutOfBoundsException if the size is empty or if it's outside of the given range
     */
    private void checkIfOutOfRange (int i, int size) throws IndexOutOfBoundsException {
        boolean isWithinRange = checkIndex(i, size);

        if (!isWithinRange || isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Increases the size of the array if it's full
     */
    private void resizeIfNeeded () {
        if (values.length == size) {
            resize();
        }
    }
}
