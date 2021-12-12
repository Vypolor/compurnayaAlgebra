package utils;

import java.math.BigInteger;

public class Converter {
    public static Integer getIntegerFromBigInteger(BigInteger n) {
        String integer = n.toString();
        return Integer.parseInt(integer);
    }

    public static BigInteger getBigIntegerFromInteger(Integer n) {
        String bigInteger = n.toString();
        return new BigInteger(bigInteger);
    }
}
