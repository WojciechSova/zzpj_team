/**
 * This example is based on example from the book:
 * <p>
 * Pragmatic Unit Testing in Java with JUnit by:
 * Andy Hunt
 * Dave Thomas
 * <p>
 * All rights belong to the authors of the book.
 */
package pl.lodz.p.zzpj.testing.junit.stack;

public class StackExercise {

    private static final int INITIAL_ARRAY_SIZE = 10;
    private static final int INTERNAL_ARRAY_INCREASE_STEP = 10;
    private String[] internalArray = new String[INITIAL_ARRAY_SIZE];

    private int currentIndex = -1;

    /**
     * Return and remove the most recent item from the top of the stack. Throws
     * StackEmptyException if the stack is empty
     */
    public String pop() throws StackEmptyException {
        if (currentIndex == -1) {
            throw new StackEmptyException();
        }

        return internalArray[currentIndex--];
    }

    /**
     * Add an item to the top of the stack.
     */
    public void push(String item) {
        if (currentIndex == internalArray.length - 1) {
            increaseInternalArray();
        }

        internalArray[++currentIndex] = item;
    }

    private void increaseInternalArray() {
        String[] extendedArray = new String[internalArray.length + INTERNAL_ARRAY_INCREASE_STEP];
        for (int i = 0; i < internalArray.length; i++) {
            extendedArray[i] = internalArray[i];
        }
        internalArray = extendedArray;
    }

    /**
     * Return but do not remove the most recent item from the top of the stack.
     * Throws StackEmptyException if the stack is empty
     */
    public String top() throws StackEmptyException {
        return internalArray[currentIndex];
    }

    /**
     * Returns true if the stack is empty.
     */
    public boolean isEmpty() {
        return currentIndex == -1;
    }

}
