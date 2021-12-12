package utils;

import java.math.BigDecimal;

public class Legendre {

    private static final BigDecimal BM1 = BigDecimal.valueOf(-1);
    private static final BigDecimal B0 = BigDecimal.ZERO;
    private static final BigDecimal B1 = BigDecimal.ONE;
    private static final BigDecimal B2 = BigDecimal.valueOf(2);
    private static final BigDecimal B4 = BigDecimal.valueOf(4);
    private static final BigDecimal B8 = BigDecimal.valueOf(8);

    public static int getSymbol(BigDecimal a, BigDecimal p) {
        if (a.equals(B1)) {
            return 1;
        }
        if (p.equals(B0)){
            return 0;
        }
        if (a.remainder(p).equals(B0)) {
            return 0;
        }
        if (a.remainder(B2).equals(B0)) {
            BigDecimal indicator = getIndicatorForEvenNumber(p);
            BigDecimal param1 = a.divide(B2);
            BigDecimal param2 = p;

            BigDecimal currentSymbolB = getIntermediateSymbol(indicator, param1, param2);
            int currentSymbol = currentSymbolB.intValue();
            System.out.println("Intermediate result: " + param1 + "/" + param2);
            return currentSymbol;
        } else {
            BigDecimal indicator = getIndicatorForOddNumber(a,p);
            BigDecimal param1 = p.remainder(a);
            BigDecimal param2 = a;

            BigDecimal currentSymbolB = getIntermediateSymbol(indicator, param1, param2);;
            int currentSymbol = currentSymbolB.intValue();
            System.out.println("Intermediate result: " + param1 + "/" + param2);
            return currentSymbol;
        }
    }

    private static BigDecimal powerBig(BigDecimal base, BigDecimal exponent) {

        if (base.equals(BM1)) {
            if (exponent.remainder(B2).equals(B0)) {
                return B1;
            }
            else
            {
                return BM1;
            }
        }


        BigDecimal ans=  B1;
        BigDecimal k=  B1;
        BigDecimal t=  BM1;
        BigDecimal no=  B0;

        if (exponent != no) {
            BigDecimal absExponent =  exponent.signum() > 0 ? exponent : t.multiply(exponent);
            while (absExponent.signum() > 0){
                ans =ans.multiply(base);
                absExponent = absExponent.subtract(B1);
            }

            if (exponent.signum() < 0) {
                // For negative exponent, must invert
                ans = k.divide(ans);
            }
        } else {
            // exponent is 0
            ans = k;
        }

        return ans;
    }

    private static BigDecimal getIndicatorForEvenNumber(BigDecimal p) {
        BigDecimal indicatorNumerator = (powerBig(p, B2)).subtract(B1);
        return indicatorNumerator.divide(B8);
    }

    private static BigDecimal getIndicatorForOddNumber(BigDecimal oddNum, BigDecimal p) {
        BigDecimal indicatorNumerator1 = oddNum.subtract(B1);
        BigDecimal indicatorNumerator2 = p.subtract(B1);
        BigDecimal indicatorNumerator = indicatorNumerator1.multiply(indicatorNumerator2);
        return indicatorNumerator.divide(B4);
    }

    private static BigDecimal getIntermediateSymbol(BigDecimal indicator, BigDecimal param1, BigDecimal param2) {
        BigDecimal multiplier = powerBig(BM1, indicator);
        return multiplier.multiply(BigDecimal.valueOf(getSymbol(param1, param2)));
    }
}
