package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Calculator {

    public static BigInteger roundedSqrtN(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument");
        }
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
            return n;
        }
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger sqrValue;
        for (sqrValue = n.divide(two); sqrValue
                .compareTo(n.divide(sqrValue)) > 0; sqrValue = ((n.divide(sqrValue)).add(sqrValue).divide(two)));
        return sqrValue.add(BigInteger.ONE);
    }

    public static BigInteger sqrtN(BigInteger n) {
        return roundedSqrtN(n).subtract(BigInteger.ONE);
    }

    private static BigInteger calculateQfromX(BigInteger n, BigInteger m) {
        BigInteger sqr = m;
        sqr = sqr.multiply(sqr);
        return sqr.subtract(n);
    }

    public static Map<BigInteger, BigInteger> getPairTableD(Integer a, BigInteger sqrtN, BigInteger n) {
        Map<BigInteger, BigInteger> dTable = new HashMap<>();
        BigInteger m;
        for(int i = 0; i <= a; ++i) {
            m = sqrtN.add(Converter.getBigIntegerFromInteger(i));
            dTable.put(m, calculateQfromX(n, m));
        }
        return dTable;
    }

    private static List<BigInteger> getKeysByValues(List<BigInteger> divide, Map<BigInteger, BigInteger> dTable) {
        List<BigInteger> keys = new ArrayList<>();
        for(Map.Entry<BigInteger, BigInteger> curTableElem : dTable.entrySet()) {
            for(BigInteger curDivide : divide) {
                if(curDivide.equals(curTableElem.getValue())) {
                    keys.add(curTableElem.getKey());
                }
            }
        }
        return keys;
    }

    public static List<List<BigInteger>> decompose(List<List<Integer>> resolutions
            , List<BigInteger> divide, Map<BigInteger, BigInteger> dTable, BigInteger n) {
        List<BigInteger> keys = getKeysByValues(divide, dTable);
        List<BigInteger> yList = calculateY(resolutions, divide);
        List<BigInteger> xList = calculateX(resolutions, keys);
        List<BigInteger> deltaList = calculateDeltaList(yList, xList);

        return decompose(deltaList, n);
    }

    private static List<BigInteger> calculateDeltaList(List<BigInteger> yList, List<BigInteger> xList) {
        List<BigInteger> result = new ArrayList<>();
        for(int i = 0; i < yList.size(); ++i) {
            result.add(xList.get(i).subtract(yList.get(i)));
        }
        return result;
    }

    private static List<BigInteger> calculateY(List<List<Integer>> resolutions, List<BigInteger> divide) {
        List<BigInteger> result = new ArrayList<>();
        for(List<Integer> curResolution : resolutions) {
            BigInteger sum = new BigInteger("1");
            for(int i = 0; i < curResolution.size(); ++i) {
                if(curResolution.get(i) == 1) {
                    sum = sum.multiply(divide.get(i));
                }
            }
            result.add(sqrtN(sum));
        }
        return result;
    }

    private static List<BigInteger> calculateX(List<List<Integer>> resolutions, List<BigInteger> keys) {
        List<BigInteger> result = new ArrayList<>();
        for(List<Integer> curResolution : resolutions) {
            BigInteger sum = new BigInteger("1");
            for(int i = 0; i < curResolution.size(); ++i) {
                if(curResolution.get(i) == 1) {
                    sum = sum.multiply(keys.get(i));
                }
            }
            result.add(sum);
        }
        return result;
    }

    private static List<List<BigInteger>> decompose(List<BigInteger> deltaList, BigInteger n) {
        List<List<BigInteger>> result = new ArrayList<>();
        for(BigInteger curDelta : deltaList) {
            BigInteger gcdValue = curDelta.gcd(n);
            if(!gcdValue.equals(n)) {
                List<BigInteger> curResolution = new ArrayList<>();
                curResolution.add(gcdValue);
                curResolution.add(n.divide(gcdValue));
                result.add(curResolution);
            }
        }
        return result;
    }

    public static Set<List<BigInteger>> getUniqueResolutions(List<List<BigInteger>> resultNotUniqueList) {
        Set<List<BigInteger>> result = new HashSet<>(resultNotUniqueList);
        return result;
    }

}
