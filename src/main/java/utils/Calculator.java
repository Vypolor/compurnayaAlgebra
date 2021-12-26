package utils;

import org.apache.commons.lang3.tuple.MutablePair;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.BigIntegerConstants.B1;

public class Calculator {

    private static final Double C_CONST = 0.71;

    private static final String X_VARIABLE = "X";
    private static final String Y_VARIABLE = "Y";

    public static BigInteger calculateB(BigInteger nParam) {
        double sqrt = Math.sqrt(Math.log(nParam.doubleValue()) * Math.log(Math.log(nParam.doubleValue())));
        double lFunctionValue = Math.pow(Math.E, sqrt);
        double bParam = Math.pow(lFunctionValue, C_CONST);
        BigDecimal bParamDecimal = new BigDecimal(String.valueOf(bParam));
        return bParamDecimal.toBigInteger();
    }

    public static MutablePair<List<BigInteger>, List<BigInteger>> prepareTableForSieve(Integer aParam, BigInteger nParam) {
        MutablePair<List<BigInteger>, List<BigInteger>> tableForSieve = new MutablePair<>();
        BigInteger sqrtN = nParam.sqrt().add(B1);

        List<BigInteger> indexes = new ArrayList<>();
        List<BigInteger> valuesForSieve = new ArrayList<>();

        for(int i = 0; i <= aParam; ++i) {
            BigInteger x = sqrtN.add(Converter.getBigIntegerFromInteger(i));
            BigInteger q = calculateQfromX(nParam, x);

            indexes.add(x);
            valuesForSieve.add(q);
        }
        tableForSieve.setLeft(indexes);
        tableForSieve.setRight(valuesForSieve);
        return tableForSieve;
    }

    public static List<List<BigInteger>> decompose(List<List<Integer>> equationsSystemResolutions,
                                                   List<BigInteger> siftedValues,
                                                   List<BigInteger> keysOfSiftedValues, BigInteger n) {
        List<BigInteger> yList = calculateVariable(equationsSystemResolutions, siftedValues, Y_VARIABLE);
        List<BigInteger> xList = calculateVariable(equationsSystemResolutions, keysOfSiftedValues, X_VARIABLE);
        List<BigInteger> deltaList = calculateDeltaList(yList, xList);

        return decompose(deltaList, n);
    }

    public static Set<List<BigInteger>> getUniqueResolutions(List<List<BigInteger>> resultNotUniqueList) {
        return new HashSet<>(resultNotUniqueList);
    }

    private static BigInteger calculateQfromX(BigInteger q, BigInteger x) {
        BigInteger sqrX = x.pow(2);
        return sqrX.subtract(q);
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

    private static List<BigInteger> calculateDeltaList(List<BigInteger> yList, List<BigInteger> xList) {
        List<BigInteger> result = new ArrayList<>();
        for(int i = 0; i < yList.size(); ++i) {
            result.add(xList.get(i).subtract(yList.get(i)));
        }
        return result;
    }

    private static List<BigInteger> calculateVariable(List<List<Integer>> resolutions, List<BigInteger> divide, String calculableVariable) {
        List<BigInteger> result = new ArrayList<>();
        for(List<Integer> curResolution : resolutions) {
            BigInteger sum = B1;
            for(int i = 0; i < curResolution.size(); ++i) {
                if(curResolution.get(i) == 1) {
                    sum = sum.multiply(divide.get(i));
                }
            }
            if (calculableVariable.equals(X_VARIABLE))
            {
                result.add(sum);
            }
            else if (calculableVariable.equals(Y_VARIABLE))
            {
                result.add(sum.sqrt());
            }
            else {
                throw new RuntimeException("Could not find variable. Must be X or Y");
            }

        }
        return result;
    }
}
