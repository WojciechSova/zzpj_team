package pl.lodz.p.zzpj.testing.junit.calculator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class SimpleCalculatorTest {

    SimpleCalculator sut;


    @BeforeClass
    public static void beforeClass() {

    }

    @Before
    public void setUp() {
        sut = new SimpleCalculator();
    }


    @Test
    public void shouldAddTwoNumbers() {

    }

    @Test
    @Parameters({"3", "7", "73"})
    public void shouldCheckIfNumberIsPrime(int prime) {

    }

    @Test
    @Parameters(method = "getTestSet")
    public void shouldCheckIfNumberIsPrimeOrNot(int prime, boolean resultFlag) {
        assertEquals(resultFlag, sut.isPrime(prime));
    }

    public Object[] getTestSet() {
        return $(
                $(3, true),
                $(5, true),
                $(21, false)
        );
    }

    @Test(expected = CannotDivideByZeroException.class)
    public void shouldThrowAnException() throws CannotDivideByZeroException {

    }
}