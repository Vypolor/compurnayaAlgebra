package qsalgorithm;

import eqations.EquationsSystemResolver;
import eqations.TableSystemEquation;
import org.apache.commons.lang3.tuple.MutablePair;
import utils.Calculator;
import utils.FactorBase;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public class QSAlgorithm {
    public static void execute(BigInteger nParam, Integer aParam) {

        BigInteger beta = Calculator.calculateB(nParam);

        FactorBase factorBase = new FactorBase(beta, nParam);
        Set<BigInteger> base = factorBase.getBase();
        MutablePair<List<BigInteger>, List<BigInteger>> tableForSieve = Calculator.prepareTableForSieve(aParam, nParam);
        List<BigInteger> indexes = tableForSieve.getLeft();
        List<BigInteger> valuesForSieve = tableForSieve.getRight();
        //==============================================================
        System.out.println("========= FACTOR BASE =========");
        System.out.println(base);
        //==============================================================
        List<Integer> sievedValuesIndexes = Sieve.doSift(valuesForSieve, base);
        List<BigInteger> sievedValues = Sieve.getSievedValuesByIndexes(valuesForSieve, sievedValuesIndexes);
        List<BigInteger> keysSievedValues = Sieve.getSievedValuesByIndexes(indexes, sievedValuesIndexes);

        List<List<Integer>> systemEquation = TableSystemEquation.getSystemEquation(sievedValues, base);
        List<List<Integer>> resolve = EquationsSystemResolver.resolve(systemEquation);
        List<List<BigInteger>> resultNotUniqueList = Calculator.decompose(resolve, sievedValues, keysSievedValues, nParam);
        Set<List<BigInteger>> uniqueResolutions = Calculator.getUniqueResolutions(resultNotUniqueList);

        System.out.println("========= DECOMPOSED NUMBERS =========");
        System.out.println(uniqueResolutions);
    }
}
