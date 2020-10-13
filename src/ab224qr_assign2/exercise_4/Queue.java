package ab224qr_assign2.exercise_4;

import java.util.NoSuchElementException;

public interface Queue<E> extends Iterable<E> {
    // Current queue size
    int size();

    // True if queue is empty
    boolean isEmpty();

    // Add element at end of queue
    void enqueue(E element);

    // Return and remove first element
    E dequeue() throws NoSuchElementException;

    // Return (without removing) first element
    E first() throws NoSuchElementException;

    // Return (without removing) last element
    E last() throws NoSuchElementException;
}
