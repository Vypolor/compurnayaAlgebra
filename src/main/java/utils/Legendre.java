package utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Legendre {

    private static final double EPSILON = 0.00001;

    public static int getSymbol(double a, double p) {
        if (a - 1.0 < EPSILON) {
            return 1;
        }
        if (a % p == 0) {
            return 0;
        }
        if (a % 2.0 == 0) {
            double indicator = (Math.pow(p, 2) - 1)/8.0;
            int currentSymbol = (int)(getSymbol(a/2, p) * (Math.pow(-1, indicator)));
            System.out.println("Intermediate result: " + a/2 + "/" + p);
            return currentSymbol;
        } else {
            double indicator = ((a-1)*(p-1))/4.0;
            int currentSymbol = (int)(getSymbol(p%a, a) * (Math.pow(-1, indicator)));
            System.out.println("Intermediate result: " + p%a + "/" + a);
            return currentSymbol;
        }
    }

    public static int getSymbolB(BigDecimal a, BigDecimal p) {
        if (a.subtract(BigDecimal.valueOf(1.0)).equals(BigDecimal.ZERO)) {
            return 1;
        }
        if (gcdB(a,p).equals(BigDecimal.valueOf(0.0))) {
            return 0;
        }
        if (gcdB(a,BigDecimal.valueOf(2.0)).equals(BigDecimal.valueOf(0.0))) {
            BigDecimal indicatorN = (powB(p, BigDecimal.valueOf(2.0))).subtract(BigDecimal.ONE);
            BigDecimal indicator = indicatorN.divide(BigDecimal.valueOf(8.0));
            int symbol = getSymbolB(a.divide(BigDecimal.valueOf(2.0)), p);
            BigDecimal symbolB = BigDecimal.valueOf(symbol);
            BigDecimal currentSymbol = symbolB.multiply(powB(BigDecimal.valueOf(-1.0), indicator));
            System.out.println("Intermediate result: " + a.divide(BigDecimal.valueOf(2.0)) + "/" + p);
            return Integer.parseInt(currentSymbol.toString());
        } else {
            BigDecimal indicatorN1 = a.subtract(BigDecimal.ONE);
            BigDecimal indicatorN2 = p.subtract(BigDecimal.ONE);
            BigDecimal indicatorN = indicatorN1.multiply(indicatorN2);
            BigDecimal indicator = indicatorN.divide(BigDecimal.valueOf(4.0));
            int symbol = getSymbolB(gcdB(p,a), a);
            BigDecimal symbolB = BigDecimal.valueOf(symbol);
            BigDecimal currentSymbol = symbolB.multiply(powB(BigDecimal.valueOf(-1.0), indicator));
            System.out.println("Intermediate result: " + gcdB(p,a) + "/" + a);
            if (currentSymbol.toString().equals("0.00") || currentSymbol.toString().contains("0E")) {
                return 0;
            }
            return Integer.parseInt(currentSymbol.toString());
        }
    }



    private static BigDecimal powB(BigDecimal a, BigDecimal exp) {
        for (BigDecimal i = BigDecimal.ZERO; i.compareTo(exp) < 1; i = i.add(BigDecimal.ONE)) {
           a = a.multiply(a);
        }
        return a;
    }

    private static BigDecimal gcdB(BigDecimal a, BigDecimal b) {
        if (b.equals(BigDecimal.valueOf(0.0)) || b.equals(BigDecimal.ZERO))
            return BigDecimal.valueOf(0.0);
        BigDecimal[] bigDecimals = a.divideAndRemainder(b);
        return bigDecimals[1];
    }
}
