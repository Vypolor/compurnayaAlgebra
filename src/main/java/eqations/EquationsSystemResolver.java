package eqations;

import java.util.ArrayList;
import java.util.List;

public class EquationsSystemResolver {
    public static List<List<Integer>> resolve(List<List<Integer>> system) {

        List<List<Integer>> resolutions = new ArrayList<>();

        BinaryNumber binaryNumber = new BinaryNumber(system.size());
        binaryNumber.increment();

        for (int i = 0; i < Math.pow(2.0, system.size())-1; ++i) {
            boolean isResolution = true;
            for (List<Integer> currentEquation : system) {
                int sum = 0;
                for (int currentVariableIndex = 0; currentVariableIndex < currentEquation.size(); ++currentVariableIndex) {
                    sum += currentEquation.get(currentVariableIndex) * binaryNumber.get(currentVariableIndex);
                }
                if (sum % 2 != 0) {
                    isResolution = false;
                    break;
                }
            }
            if (isResolution) {
                resolutions.add(new ArrayList<>(binaryNumber.getNumber()));
            }
            binaryNumber.increment();
        }
        printResolutions(resolutions);
        return resolutions;
    }

    private static void printResolutions(List<List<Integer>> equationsResolutions) {
        System.out.println("=========== EQUATIONS RESOLUTIONS ===========");
        for (int i = 0; i < equationsResolutions.size(); ++i) {
            System.out.println(equationsResolutions.get(i));
        }
    }
}
