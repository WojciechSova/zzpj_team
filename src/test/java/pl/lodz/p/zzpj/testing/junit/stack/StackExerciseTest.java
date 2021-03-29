package pl.lodz.p.zzpj.testing.junit.stack;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackExerciseTest {

    StackExercise stack;

    @Before
    public void setUp() {
        stack = new StackExercise();
    }

    @Test
    public void shouldAddItem() throws StackEmptyException {
        stack.push("1");
        assertFalse(stack.isEmpty());
        assertEquals("1", stack.top());
    }

    @Test
    public void shouldReturnMostRecentItem() throws StackEmptyException {
        stack.push("1");
        assertEquals("1", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test(expected = StackEmptyException.class)
    public void shouldThrowStackException() throws StackEmptyException {
        assertEquals("1", stack.pop());
        assertEquals("1", stack.top());
    }

    @Test
    public void shouldReturnMostRecentItemWithoutRemoving() throws StackEmptyException {
        stack.push("1");
        assertEquals("1", stack.top());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void shouldReturnWhetherStackIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push("1");
        assertFalse(stack.isEmpty());
    }
}
