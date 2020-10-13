/*
 * Date: 2020-09-25
 * File Name: QueueTest.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueTest {
    private QueueExample<Integer> sut;

    @BeforeEach
    void setUp() {
        this.sut = new QueueExample<>();
    }

    @Test
    void shouldHaveEmptyQueue() {
        int expected = 0;
        int actual = this.sut.size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnTrueWhenCheckingIfQueueIsEmpty() {
        boolean isEmpty = this.sut.isEmpty();
        Assertions.assertTrue(isEmpty);
    }

    @Test
    void shouldReturnFalseWhenQueueIsNotEmpty() {
        this.sut.enqueue(1);

        Assertions.assertFalse(this.sut.isEmpty());
    }

    @Test
    void shouldEnqueue4Elements() {
        int amountToEnqueue = 4;
        addNodes(amountToEnqueue);

        Assertions.assertEquals(amountToEnqueue, this.sut.size());
    }

    @Test
    void shouldBeAbleToEnqueue100000Elements() {
        int amountToEnqueue = 100000;
        addNodes(amountToEnqueue);

        Assertions.assertEquals(amountToEnqueue, this.sut.size());
    }

    @Test
    void shouldDequeue4Elements() {
        addNodes(10);

        int amountToDequeue = 4;
        removeNodes(amountToDequeue);

        int expected = 6;

        Assertions.assertEquals(expected, this.sut.size());
    }

    @Test
    void shouldDequeue100000Elements() {
        int amountToEnqueue = 100001;
        addNodes(amountToEnqueue);

        int amountToDequeue = 100000;
        removeNodes(amountToDequeue);

        int expected = 1;

        Assertions.assertEquals(expected, this.sut.size());
    }

    @Test
    void shouldThrowErrorWhenDequeueEmptyQueue() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.sut.dequeue();
        });
    }

    @Test
    void shouldReturnFirstNode() {
        this.sut.enqueue(5);
        this.sut.enqueue(10);

        int actual = this.sut.first();

        Assertions.assertEquals(5, actual);
    }

    @Test
    void shouldThrowErrorWhenCallingFirstOnEmptyQueue() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.sut.first();
        });
    }

    @Test
    void shouldReturnLastNode() {
        this.sut.enqueue(7);
        this.sut.enqueue(12);

        int actual = this.sut.last();

        Assertions.assertEquals(12, actual);
    }

    @Test
    void shouldThrowErrorWhenCallingLastOnEmptyQueue() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.sut.last();
        });
    }

    @Test
    void firstAndLastShouldBeSameWhenOnlyOneInQueue() {
        this.sut.enqueue(20);
        int first = this.sut.first();
        int last = this.sut.last();

        Assertions.assertEquals(first, last);
    }

    @Test
    void shouldReturnTrueWhenIteratorHasNext() {
        addNodes(3);
        Assertions.assertTrue(this.sut.iterator().hasNext());
    }

    @Test
    void shouldReturnFalseWhenIteratorNotHasNext() {
        Assertions.assertFalse(this.sut.iterator().hasNext());
    }

    @Test
    void shouldReturnFalseWhenIteratorNotHasNextAfterDequeue() {
        this.sut.enqueue(1);
        this.sut.enqueue(2);

        Iterator<Integer> iterator = this.sut.iterator();

        this.sut.dequeue();
        this.sut.dequeue();

        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void shouldReturnCurrentElementWhenCallingNext() {
        this.sut.enqueue(1);
        this.sut.enqueue(2);
        int previous = this.sut.iterator().next();

        Assertions.assertEquals(1, previous);
    }

    @Test
    void shouldUpdateCurrentToNext() {
        this.sut.enqueue(1);
        this.sut.enqueue(2);
        this.sut.enqueue(3);

        Iterator<Integer> iterator = this.sut.iterator();

        Assertions.assertEquals(1, (int) iterator.next());
        Assertions.assertEquals(2, (int) iterator.next());
        Assertions.assertEquals(3, (int) iterator.next());
    }

    @Test
    void shouldUpdateIteratorAfterEnqueue() {
        Iterator<Integer> iterator = this.sut.iterator();

        this.sut.enqueue(1);
        this.sut.enqueue(2);
        this.sut.enqueue(3);

        Assertions.assertEquals(1, (int) iterator.next());
        Assertions.assertEquals(2, (int) iterator.next());
        Assertions.assertEquals(3, (int) iterator.next());
    }

    @Test
    void shouldUpdateIteratorAfterDequeue() {
        Iterator<Integer> iterator = this.sut.iterator();

        this.sut.enqueue(1);
        this.sut.enqueue(2);
        this.sut.dequeue();

        Assertions.assertEquals(2, (int) iterator.next());
    }

    /**
     * Helper method to enqueue
     * @param amount the number of times to enqueue
     */
    private void addNodes(int amount) {
        for (int i = 1; i <= amount; i++) {
            this.sut.enqueue(i);
        }
    }

    /**
     * Helper method to dequeue
     * @param amount the number of times to dequeue
     */
    private void removeNodes(int amount) {
        for (int i = 1; i <= amount; i++) {
            this.sut.dequeue();
        }
    }

    @Test
    void shouldAcceptStrings() {
        QueueExample<String> queue = new QueueExample<>();
        String element = "Hello world";
        queue.enqueue(element);

        Assertions.assertEquals(element, queue.first());
    }

    @Test
    void shouldAcceptDoubles() {
        QueueExample<Double> queue = new QueueExample<>();
        Double element = 2.5;
        queue.enqueue(element);

        Assertions.assertEquals(element, queue.first());
    }

    @Test
    void shouldAcceptIntArray() {
        QueueExample<int[]> queue = new QueueExample<>();
        int[] array = {5,3,1};
        queue.enqueue(array);

        Assertions.assertEquals(array, queue.first());
    }
}
