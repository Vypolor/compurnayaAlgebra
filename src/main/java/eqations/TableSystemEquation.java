package eqations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TableSystemEquation {

    public static List<List<Integer>> getSystemEquation (List<BigInteger> sievedValues, Set<BigInteger> base) {
        List<List<Integer>> result = new ArrayList<>();

        for(BigInteger currentDivide : sievedValues) {

            List<Integer> listForCurrentDivide = new ArrayList<>();

            for(BigInteger currentBase : base) {

                boolean isAtLeastOneValueDivided = true;
                int currentUsages = 0;

                while(isAtLeastOneValueDivided) {

                    isAtLeastOneValueDivided = false;
                    BigInteger[] resultAndRemainder = currentDivide
                            .divideAndRemainder(currentBase);

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
        List<List<Integer>> resultMatrix = convertMatrix(getMatrixModTwo(result));
        printMatrix(resultMatrix);
        return resultMatrix;
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

    private static void printMatrix(List<List<Integer>> resultMatrix) {
        System.out.println("=========== RESULT MATRIX ===========");
        for (int i = 0; i < resultMatrix.size(); ++i) {
            System.out.println(resultMatrix.get(i));
        }
    }
}
