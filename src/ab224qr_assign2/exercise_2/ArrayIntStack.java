/*
 * Date: 2020-09-23
 * File Name: ArrayIntStack.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_2;

import two_dv600.AbstractIntCollection;
import two_dv600.IntStack;

/**
 * Class Description: Implements methods for working with integer stacks
 * @version 1.0
 * @author Adam Bergman
 */
public class ArrayIntStack extends AbstractIntCollection implements IntStack {
    /**
     * Adds integer to the top (end) of the stack
     * @param n the integer to add
     */
    @Override
    public void push(int n) {
        if (values.length == size) {
            resize();
        }

        values[size++] = n;
    }

    /**
     * Removes and returns the integer at top (end) of the stack
     * @return the removed integer
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    @Override
    public int pop() throws IndexOutOfBoundsException {
        checkIfEmpty();

        int removed = values[size - 1];
        size--;

        return removed;
    }

    /**
     * Returns the integer at top (end) of the stack
     * @return the integer at top (end) of the stack
     * @throws IndexOutOfBoundsException if the stack is empty
     */
    @Override
    public int peek() throws IndexOutOfBoundsException {
        checkIfEmpty();

        return values[size - 1];
    }

    /**
     * Helper method that throws an error if the stack is empty
     */
    private void checkIfEmpty() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
