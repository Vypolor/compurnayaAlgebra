package utils;

import java.math.BigInteger;

import static utils.BigIntegerConstants.*;

public class Legendre {

    public static int getSymbol(BigInteger k, BigInteger n) {
        if (k.compareTo(B0) < 0 || n.remainder(B2).equals(B0)) {
            throw new IllegalArgumentException("Invalid value. k = " + k + ", n = " + n);
        }
        k = k.remainder(n);
        int jacobi = 1;
        while (k.compareTo(B0) > 0) {
            while (k.remainder(B2).equals(B0)) {
                k = k.divide(B2);
                BigInteger r = n.remainder(B8);
                if ( r.equals(B3) || r.equals(B3) ) {
                    jacobi = -jacobi;
                }
            }
            BigInteger temp = n;
            n = k;
            k = temp;
            if ( k.remainder(B4).equals(B3) &&
                    n.remainder(B4).equals(B3) ) {
                jacobi = -jacobi;
            }
            k = k.remainder(n);
        }
        if ( n.equals(B1)) {
            return jacobi;
        }
        return 0;
    }
}
