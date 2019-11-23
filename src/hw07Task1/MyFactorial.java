package hw07Task1;

import java.math.BigInteger;
import java.util.concurrent.Callable;

class MyFactorial implements Callable<BigInteger> {
    private int number;

    public MyFactorial (int number) {
        this.number = number;
    }

    @Override
    public BigInteger call() {
        BigInteger total = new BigInteger(String.valueOf(1));
        for (int i = 1; i <= number; i++) {
            total = total.multiply(BigInteger.valueOf(i));
        }
        return total;
    }
}