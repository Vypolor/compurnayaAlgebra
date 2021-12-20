package utils;

import java.math.BigInteger;

public class Legendre {

    private static final BigInteger BM1 = BigInteger.valueOf(-1);
    private static final BigInteger B0 = BigInteger.ZERO;
    private static final BigInteger B1 = BigInteger.ONE;
    private static final BigInteger B2 = BigInteger.valueOf(2);
    private static final BigInteger B4 = BigInteger.valueOf(4);
    private static final BigInteger B8 = BigInteger.valueOf(8);

    public static int getSymbol(BigInteger k, BigInteger n) {
        if (k.compareTo(BigInteger.ZERO) < 0 || n.remainder(BigInteger.valueOf(2L)).equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Invalid value. k = " + k + ", n = " + n);
        }
        k = k.remainder(n);
        int jacobi = 1;
        while (k.compareTo(BigInteger.ZERO) > 0) {
            while (k.remainder(BigInteger.valueOf(2L)).equals(BigInteger.ZERO)) {
                //System.out.println(k + "/" + n);
                k = k.divide(BigInteger.valueOf(2L));
                BigInteger r = n.remainder(BigInteger.valueOf(8L));
                if ( r.equals(BigInteger.valueOf(3L)) || r.equals(BigInteger.valueOf(3L)) ) {
                    jacobi = -jacobi;
                }
            }
            /*if (!k.equals(n)){
                System.out.println(k + "/" + n);
            }*/
            BigInteger temp = n;
            n = k;
            k = temp;
            if ( k.remainder(BigInteger.valueOf(4L)).equals(BigInteger.valueOf(3L)) &&
                    n.remainder(BigInteger.valueOf(4L)).equals(BigInteger.valueOf(3L)) ) {
                jacobi = -jacobi;
            }
            k = k.remainder(n);
        }
        if ( n.equals(BigInteger.ONE)) {
            return jacobi;
        }
        return 0;
    }
}
