import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.Converter;

public class Divider {
    public static List<BigInteger> divide(Map<Integer, BigInteger> dTable, Set<Integer> factorBase) {
        Map<Integer, BigInteger> result = cloneMap(dTable);
        for(Integer currentBase : factorBase) {
            boolean isAtLeastOneValueDivided = true;
            BigInteger bigIntegerCurrentBase = Converter.getBigIntegerFromInteger(currentBase);
            while(isAtLeastOneValueDivided) {
                isAtLeastOneValueDivided = false;
                for(Map.Entry<Integer, BigInteger> currentResultElem : result.entrySet()) {
                    BigInteger[] resultAndRemainder = currentResultElem.getValue()
                            .divideAndRemainder(bigIntegerCurrentBase);
                    if(resultAndRemainder[1].equals(BigInteger.ZERO)) {
                        result.put(currentResultElem.getKey(), resultAndRemainder[0]);
                        isAtLeastOneValueDivided = true;
                    }
                }
            }
        }
        return getResultValuesVector(result, dTable);
    }

    public static List<BigInteger> divideB(Map<BigInteger, BigInteger> dTable, Set<Integer> factorBase) {
        Map<BigInteger, BigInteger> result = cloneMapB(dTable);
        for(Integer currentBase : factorBase) {
            boolean isAtLeastOneValueDivided = true;
            BigInteger bigIntegerCurrentBase = Converter.getBigIntegerFromInteger(currentBase);
            while(isAtLeastOneValueDivided) {
                isAtLeastOneValueDivided = false;
                for(Map.Entry<BigInteger, BigInteger> currentResultElem : result.entrySet()) {
                    BigInteger[] resultAndRemainder = currentResultElem.getValue()
                            .divideAndRemainder(bigIntegerCurrentBase);
                    if(resultAndRemainder[1].equals(BigInteger.ZERO)) {
                        result.put(currentResultElem.getKey(), resultAndRemainder[0]);
                        isAtLeastOneValueDivided = true;
                    }
                }
            }
        }
        return getResultValuesVectorB(result, dTable);
    }

    private static List<BigInteger> getResultValuesVector(Map<Integer, BigInteger> resultTable
            , Map<Integer, BigInteger> dTable) {
        List<BigInteger> result = new ArrayList<>();
        for(Map.Entry<Integer, BigInteger> currentResultTableElem : resultTable.entrySet()) {
            if(currentResultTableElem.getValue().equals(BigInteger.ONE)) {
                result.add(dTable.get(currentResultTableElem.getKey()));
            }
        }
        return result;
    }

    private static List<BigInteger> getResultValuesVectorB(Map<BigInteger, BigInteger> resultTable
            , Map<BigInteger, BigInteger> dTable) {
        List<BigInteger> result = new ArrayList<>();
        for(Map.Entry<BigInteger, BigInteger> currentResultTableElem : resultTable.entrySet()) {
            if(currentResultTableElem.getValue().equals(BigInteger.ONE)) {
                result.add(dTable.get(currentResultTableElem.getKey()));
            }
        }
        return result;
    }

    private static Map<Integer, BigInteger> cloneMap (Map<Integer, BigInteger> dTable) {
        Map<Integer, BigInteger> clone = new HashMap<>();
        for(Map.Entry<Integer, BigInteger> currentElem : dTable.entrySet()) {
            clone.put(new Integer(currentElem.getKey().toString()), new BigInteger(currentElem.getValue().toString()));
        }
        return clone;
    }

    private static Map<BigInteger, BigInteger> cloneMapB (Map<BigInteger, BigInteger> dTable) {
        Map<BigInteger, BigInteger> clone = new HashMap<>();
        for(Map.Entry<BigInteger, BigInteger> currentElem : dTable.entrySet()) {
            clone.put(new BigInteger(currentElem.getKey().toString()), new BigInteger(currentElem.getValue().toString()));
        }
        return clone;
    }

}
