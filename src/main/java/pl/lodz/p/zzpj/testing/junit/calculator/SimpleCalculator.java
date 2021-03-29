package pl.lodz.p.zzpj.testing.junit.calculator;

import java.util.stream.IntStream;

public class SimpleCalculator {
    public double add(double number1, double number2) {

        return number1 + number2;
    }

    public double subtract(double number1, double number2) {
        return number1 - number2;
    }

    public double divide(double numerator, double denominator) throws CannotDivideByZeroException {
        if (denominator == 0.0d) {
            throw new CannotDivideByZeroException("Can't by zero!");
        }

        return numerator / denominator;
    }

    public double calculateSquareRoot(double number) throws CannotCalculateSquareRootOfNegativeNumber {

        if (number < 0) {
            throw new CannotCalculateSquareRootOfNegativeNumber(number);
        }

        return Math.sqrt(number);

    }

    public int modulo(int a, int b) {
        return a % b;
    }

    public boolean isPrime(int a) {

        //Java 8
        return !IntStream.range(2, a).anyMatch(e -> modulo(a, e) == 0);

        //before Java 8
//		for (int i = 2; i < a; i++) {
//			if(modulo(a, i)==0)
//				return false;
//		}
//		return true;
    }
}

class CannotDivideByZeroException extends Exception {
    public CannotDivideByZeroException(String message) {
        super(message);
    }
}

class CannotCalculateSquareRootOfNegativeNumber extends Exception {

    private double number;

    public CannotCalculateSquareRootOfNegativeNumber(double number) {
        this.number = number;
    }

    public double getNumberThatCausedException() {
        return number;
    }

}