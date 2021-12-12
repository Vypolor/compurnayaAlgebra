import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Divider {

    public static List<BigInteger> divide(Map<BigInteger, BigInteger> dTable, Set<BigInteger> factorBase) {
        Map<BigInteger, BigInteger> result = cloneMap(dTable);
        for(BigInteger currentBase : factorBase) {
            boolean isAtLeastOneValueDivided = true;
            while(isAtLeastOneValueDivided) {
                isAtLeastOneValueDivided = false;
                for(Map.Entry<BigInteger, BigInteger> currentResultElem : result.entrySet()) {
                    BigInteger[] resultAndRemainder = currentResultElem.getValue()
                            .divideAndRemainder(currentBase);
                    if(resultAndRemainder[1].equals(BigInteger.ZERO)) {
                        result.put(currentResultElem.getKey(), resultAndRemainder[0]);
                        isAtLeastOneValueDivided = true;
                    }
                }
            }
        }
        return getResultValuesVector(result, dTable);
    }

    private static List<BigInteger> getResultValuesVector(Map<BigInteger, BigInteger> resultTable
            , Map<BigInteger, BigInteger> dTable) {
        List<BigInteger> result = new ArrayList<>();
        for(Map.Entry<BigInteger, BigInteger> currentResultTableElem : resultTable.entrySet()) {
            if(currentResultTableElem.getValue().equals(BigInteger.ONE)) {
                result.add(dTable.get(currentResultTableElem.getKey()));
            }
        }
        return result;
    }

    private static Map<BigInteger, BigInteger> cloneMap(Map<BigInteger, BigInteger> dTable) {
        Map<BigInteger, BigInteger> clone = new HashMap<>();
        for(Map.Entry<BigInteger, BigInteger> currentElem : dTable.entrySet()) {
            clone.put(new BigInteger(currentElem.getKey().toString()), new BigInteger(currentElem.getValue().toString()));
        }
        return clone;
    }

}
