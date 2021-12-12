package utils;

import java.math.BigInteger;

public class PrimeChecker {
    public static boolean isPrime(int number) {
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime((int) Math.log(number));
    }
}
