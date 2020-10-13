/*
 * Date: 2020-09-23
 * File Name: CollectionMain.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_2;

/**
 * Class Description: Demonstrates different usages of ArrayIntList and ArrayIntStack
 * @version 1.0
 * @author Adam Bergman
 */
public class CollectionMain {
    /**
     * The starting point of the application
     * @param args
     */
    public static void main(String[] args) {
        demonstrateIntList();
        demonstrateIntStack();
    }

    /**
     * Demonstrates the methods implemented in ArrayIntList
     */
    private static void demonstrateIntList() {
        System.out.println("---\nArrayIntList\n---");
        ArrayIntList arrayIntList = new ArrayIntList();

        for (int i = 1; i <= 9; i++) {
            arrayIntList.add(i);
        }

        System.out.println(arrayIntList + " Size: " + arrayIntList.size());

        arrayIntList.addAt(25, 3);
        System.out.println(arrayIntList + " Size: " + arrayIntList.size());

        arrayIntList.remove(7);
        System.out.println(arrayIntList + " Size: " + arrayIntList.size() + "(Removed at index 7)");

        int numberToGet = arrayIntList.get(1);
        System.out.println("Number at position 1: " + numberToGet);

        int index = arrayIntList.indexOf(5);
        System.out.println("Index of 5 is: " + index);
    }

    /**
     * Demonstrates the methods implemented in ArrayIntStack
     */
    private static void demonstrateIntStack() {
        System.out.println("...\nArrayIntStack\n---");
        ArrayIntStack arrayIntStack = new ArrayIntStack();

        for (int i = 10; i <= 19; i++) {
            arrayIntStack.push(i);
        }

        System.out.println(arrayIntStack + " Size: " + arrayIntStack.size());

        int removed = arrayIntStack.pop();
        System.out.println("Removed: " + removed + " From: " + arrayIntStack);

        int peeked = arrayIntStack.peek();
        System.out.println("Peeked: " + peeked + " From: " + arrayIntStack);
    }
}
