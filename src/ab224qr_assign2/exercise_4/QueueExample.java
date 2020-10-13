/*
 * Date: 2020-09-25
 * File Name: QueueExample.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Description: Implements a generic queue
 * @version 1.0
 * @author Adam Bergman
 */
public class QueueExample<E> implements Queue<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    /**
     * Returns the size of the queue
     * @return the size of the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty
     * @return true if empty, else false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds element to the end of the queue
     * @param element the element to add
     */
    @Override
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);

        if (isEmpty()) {
            // Set both first and last to the same node
            first = newNode;
            last = newNode;
        } else {
            last.nextElement = newNode; // Assign it to the last node's next element
            last = last.nextElement; // Assign the last element to it's next
        }

        size++;
    }

    /**
     * Removes the first element from the queue
     * @return the removed element
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E dequeue() throws NoSuchElementException {
        throwErrIfQueueIsEmpty();

        E node = first.element; // Store the first node and return it
        first = first.nextElement; // Set the first node to it's next
        size--;

        return node;
    }

    /**
     * Get the first element in the queue
     * @return The first element in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E first() throws NoSuchElementException {
        throwErrIfQueueIsEmpty();

        return first.element;
    }

    /**
     * Get the last element in the queue
     * @return The last element in the queue
     * @throws NoSuchElementException
     */
    @Override
    public E last() throws NoSuchElementException {
        throwErrIfQueueIsEmpty();

        return last.element;
    }

    /**
     * Helper method to check if the queue is empty and then
     * throws an exception
     */
    private void throwErrIfQueueIsEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
    }

    /**
     * Represents a generic Node containing an element and it's next element
     * @param <E>
     */
    private static class Node<E> {
        private final E element;
        private Node<E> nextElement = null;

        Node(E e) {
            element = e;
        }
    }

    /**
     * Initializes the queue iterator
     * @return a new instance of the queue iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    /**
     * Private inner class used by iterator()
     */
    private class QueueIterator implements Iterator<E> {
        private Node<E> current = first;
        private int expectedSize = size();

        /**
         * Checks if the node has a defined next element
         * @return true if it has, else false
         */
        @Override
        public boolean hasNext() {
            if (expectedSize != size()) {
                current = first;
                expectedSize = size();
            }

            return current != null;
        }

        /**
         * Replaces the element with it's next value
         * @return the previous element, which was replaced by it's next
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E element = current.element;
            current = current.nextElement;

            return element;
        }
    }
}
