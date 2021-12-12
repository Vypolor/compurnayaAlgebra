package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Calculator {
    public static Integer roundedSqrtN(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negatisve argument");
        }
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
            return Converter.getIntegerFromBigInteger(n);
        }
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger sqrValue;
        for (sqrValue = n.divide(two); sqrValue
                .compareTo(n.divide(sqrValue)) > 0; sqrValue = ((n.divide(sqrValue)).add(sqrValue).divide(two)))
            ;
        Integer result = Converter.getIntegerFromBigInteger(sqrValue);
        return result + 1;
    }

    public static BigInteger roundedSqrtNB(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Negatisve argument");
        }
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
            return n;
        }
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger sqrValue;
        for (sqrValue = n.divide(two); sqrValue
                .compareTo(n.divide(sqrValue)) > 0; sqrValue = ((n.divide(sqrValue)).add(sqrValue).divide(two)))
            ;
        return sqrValue.add(BigInteger.ONE);
        /*Integer result = Converter.getIntegerFromBigInteger(sqrValue);
        return result + 1;*/
    }

    public static Integer sqrtN(BigInteger n) {
        return roundedSqrtN(n) - 1;
    }

    public static BigInteger sqrtNB(BigInteger n) {
        return roundedSqrtNB(n).subtract(BigInteger.ONE);
    }

    private static BigInteger calculateQfromX(BigInteger n, Integer m) {
        BigInteger sqr = Converter.getBigIntegerFromInteger(m);
        sqr = sqr.multiply(sqr);
        return sqr.subtract(n);
    }

    private static BigInteger calculateQfromXB(BigInteger n, BigInteger m) {
        BigInteger sqr = m;
        sqr = sqr.multiply(sqr);
        return sqr.subtract(n);
    }

    public static Map<BigInteger, BigInteger> getPairTableDB (Integer a, BigInteger sqrtN, BigInteger n) {
        Map<BigInteger, BigInteger> dTable = new HashMap<>();
        BigInteger m;
        for(int i = 0; i <= a; ++i) {
            m = sqrtN.add(Converter.getBigIntegerFromInteger(i));
            dTable.put(m, calculateQfromXB(n, m));
        }
        return dTable;
    }

    public static Map<Integer, BigInteger> getPairTableD (Integer a, Integer sqrtN, BigInteger n) {
        Map<Integer, BigInteger> dTable = new HashMap<>();
        Integer m;
        for(int i = 0; i <= a; ++i) {
            m = sqrtN + i;
            dTable.put(m, calculateQfromX(n, m));
        }
        return dTable;
    }

    private static List<Integer> getKeysByValues(List<BigInteger> divide, Map<Integer, BigInteger> dTable) {
        List<Integer> keys = new ArrayList<>();
        for(Map.Entry<Integer, BigInteger> curTableElem : dTable.entrySet()) {
            for(BigInteger curDivide : divide) {
                if(curDivide.equals(curTableElem.getValue())) {
                    keys.add(curTableElem.getKey());
                }
            }
        }
        return keys;
    }

    private static List<BigInteger> getKeysByValuesB(List<BigInteger> divide, Map<BigInteger, BigInteger> dTable) {
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
            , List<BigInteger> divide, Map<Integer, BigInteger> dTable, BigInteger n) {
        List<Integer> keys = getKeysByValues(divide, dTable);
        List<BigInteger> yList = calculateY(resolutions, divide);
        List<BigInteger> xList = calculateX(resolutions, keys);
        List<BigInteger> deltaList = calclateDeltaList(yList, xList);

        return decompose(deltaList, n);
    }

    public static List<List<BigInteger>> decomposeB(List<List<Integer>> resolutions
            , List<BigInteger> divide, Map<BigInteger, BigInteger> dTable, BigInteger n) {
        List<BigInteger> keys = getKeysByValuesB(divide, dTable);
        List<BigInteger> yList = calculateYB(resolutions, divide);
        List<BigInteger> xList = calculateXB(resolutions, keys);
        List<BigInteger> deltaList = calclateDeltaList(yList, xList);

        return decompose(deltaList, n);
    }

    private static List<BigInteger> calclateDeltaList(List<BigInteger> yList, List<BigInteger> xList) {
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
            result.add(Converter.getBigIntegerFromInteger(sqrtN(sum)));
        }
        return result;
    }

    private static List<BigInteger> calculateYB(List<List<Integer>> resolutions, List<BigInteger> divide) {
        List<BigInteger> result = new ArrayList<>();
        for(List<Integer> curResolution : resolutions) {
            BigInteger sum = new BigInteger("1");
            for(int i = 0; i < curResolution.size(); ++i) {
                if(curResolution.get(i) == 1) {
                    sum = sum.multiply(divide.get(i));
                }
            }
            result.add(sqrtNB(sum));
        }
        return result;
    }

    private static List<BigInteger> calculateX(List<List<Integer>> resolutions, List<Integer> keys) {
        List<BigInteger> result = new ArrayList<>();
        for(List<Integer> curResolution : resolutions) {
            BigInteger sum = new BigInteger("1");
            for(int i = 0; i < curResolution.size(); ++i) {
                if(curResolution.get(i) == 1) {
                    sum = sum.multiply(Converter.getBigIntegerFromInteger(keys.get(i)));
                }
            }
            result.add(sum);
        }
        return result;
    }

    private static List<BigInteger> calculateXB(List<List<Integer>> resolutions, List<BigInteger> keys) {
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
