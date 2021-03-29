package pl.lodz.p.zzpj.testing.junit.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    FizzBuzz fizzBuzz;

    @BeforeEach
    public void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Tag("1")
    @DisplayName("Mój test")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 6, 9, 10, 12, 15, 30})
    public void shouldReturnCorrectFizzBuzz(int number) {
        if (number % 15 == 0) {
            assertTrue(fizzBuzz.getFizzBuzzNumber(number).equals("FizzBuzz"));
        } else if (number % 5 == 0) {
            assertFalse(fizzBuzz.getFizzBuzzNumber(number).equals("FizzBuzz"));
            assertEquals("Buzz", fizzBuzz.getFizzBuzzNumber(number));
        } else if (number % 3 == 0) {
            assertEquals("Fizz", fizzBuzz.getFizzBuzzNumber(number));
        }
        else
            assertEquals(String.valueOf(number), fizzBuzz.getFizzBuzzNumber(number));
    }
}