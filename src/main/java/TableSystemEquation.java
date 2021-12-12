import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import utils.Converter;

public class TableSystemEquation {

    public static List<List<Integer>> getSystemEquation (List<BigInteger> divide, Set<Integer> base) {
        List<List<Integer>> result = new ArrayList<>();
        for(BigInteger currentDivide : divide) {
            List<Integer> listForCurrentDivide = new ArrayList<>();
            for(Integer currentBase : base) {
                boolean isAtLeastOneValueDivided = true;
                BigInteger bigIntegerCurrentBase = Converter.getBigIntegerFromInteger(currentBase);
                int currentUsages = 0;
                while(isAtLeastOneValueDivided) {
                    isAtLeastOneValueDivided = false;
                    BigInteger[] resultAndRemainder = currentDivide
                            .divideAndRemainder(bigIntegerCurrentBase);
                    if(resultAndRemainder[1].equals(BigInteger.ZERO)) {
                        currentDivide = resultAndRemainder[0];
                        isAtLeastOneValueDivided = true;
                        ++currentUsages;
                    }
                }
                listForCurrentDivide.add(currentUsages);
            }
            result.add(listForCurrentDivide);
        }
        return convertMatrix(getMatrixModTwo(result));
    }

    private static List<List<Integer>> getMatrixModTwo(List<List<Integer>> usagesMatrix) {
        List<List<Integer>> newMatrixList = new ArrayList<>();
        for(List<Integer> currentList : usagesMatrix) {
            List<Integer> newMatrixColumn = new ArrayList<>();
            for(Integer currentElemInCurrentList : currentList) {
                if(currentElemInCurrentList % 2 == 0) {
                    newMatrixColumn.add(0);
                }
                else {
                    newMatrixColumn.add(1);
                }
            }
            newMatrixList.add(newMatrixColumn);
        }
        return newMatrixList;
    }

    private static List<List<Integer>> convertMatrix (List<List<Integer>> resultMatrix) {
        List<List<Integer>> resultList = new ArrayList<>();
        for(int i = 0; i < resultMatrix.size(); ++i) {
            List<Integer> curList = new ArrayList<>();
            for(int j = 0; j < resultMatrix.size(); ++j) {
                curList.add(resultMatrix.get(j).get(i));
            }
            resultList.add(curList);
        }
        return resultList;
    }
}
