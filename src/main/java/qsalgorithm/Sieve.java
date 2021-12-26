package qsalgorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static utils.BigIntegerConstants.B0;
import static utils.BigIntegerConstants.B1;

public class Sieve {


    public static List<Integer> doSift(List<BigInteger> valuesForSift, Set<BigInteger> factorBase) {

        System.out.println("======================= SIFT STAGE =======================");
        List<BigInteger> copyValuesForSift = new ArrayList<>(valuesForSift);

        List<Integer> indexes = new ArrayList<>();

        for (BigInteger currentBaseElem: factorBase) {
            int exp = 1;
            while (true) {
                long step = (long)Math.pow(currentBaseElem.intValue(), exp);

                int subListSize = step > copyValuesForSift.size() - 1 ? copyValuesForSift.size()-1:(int)step;

                List<Integer> divisibleNumbersIndexes = divisibleNumbers(copyValuesForSift.subList(0, subListSize), currentBaseElem);

                if (divisibleNumbersIndexes.size() == 0) {
                    break;
                }

                for (Integer curIndex : divisibleNumbersIndexes) {

                    for (int i = curIndex; i < copyValuesForSift.size(); i += step) {
                        copyValuesForSift.set(i, copyValuesForSift.get(i).divide(currentBaseElem));

                        if (copyValuesForSift.get(i).equals(B1)) {
                            indexes.add(i);
                        }
                    }
                }
                System.out.printf("P = %s; B = %d: %s\n", currentBaseElem.toString(), exp, copyValuesForSift.toString());
                ++exp;
            }
        }

        return indexes;
    }

    private static List<Integer> divisibleNumbers(List<BigInteger> values, BigInteger divider) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < values.size(); ++i) {
            BigInteger curValue = values.get(i);
            if (curValue.remainder(divider).equals(B0)) {
                result.add(i);
            }
        }
        return result;
    }

    public static List<BigInteger> getSievedValuesByIndexes(List<BigInteger> valuesForSieve, List<Integer> indexesOfSievedValues) {
        List<BigInteger> result = new ArrayList<>();
        for (Integer currentIndex : indexesOfSievedValues) {
            result.add(valuesForSieve.get(currentIndex));
        }
        return result;
    }

}
