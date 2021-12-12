import eqations.EquationsSystemResolver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.Calculator;
import utils.FactorBase;

public class InputHandler {


    public static void main(String[] args) throws IOException {
        /*System.out.println(Legendre.getSymbol(17, 11));
        System.out.println(Legendre.getSymbolB(BigDecimal.valueOf(17), BigDecimal.valueOf(11)));*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter N, B and A values: \n[N] - value: ");
        //String temp = reader.readLine();

        BigInteger int1 = new BigInteger("99999999").nextProbablePrime();
        BigInteger int2 = new BigInteger("999999999").nextProbablePrime();
        BigInteger int3 = int1.multiply(int2);
        System.out.println(int3);
        BigInteger nParam = int3;//= new BigInteger(temp);

        System.out.println("[B] - value: ");
        BigInteger beta = new BigInteger(reader.readLine());

        System.out.println("[A] - value: ");
        Integer aParam = Integer.parseInt(reader.readLine());

        //==============================================================
        //Integer sqrt = Calculator.roundedSqrtN(nParam);
        BigInteger sqrtB = Calculator.roundedSqrtN(nParam);
        //System.out.println("sqr of N value: " + sqrt + " sqr of NB value: " + sqrtB);
        reader.close();
        //==============================================================
        //Map<Integer, BigInteger> pairTableD = Calculator.getPairTableD(aParam, sqrt, nParam);
        Map<BigInteger, BigInteger> pairTableDB = Calculator.getPairTableD(aParam, sqrtB, nParam);
        //==============================================================
        FactorBase factorBase = new FactorBase(beta, nParam);
        Set<BigInteger> base = factorBase.getBase();
        System.out.println(base);
        //==============================================================
        //List<BigInteger> divide = Divider.divide(pairTableD, base);
        List<BigInteger> divideB = Divider.divide(pairTableDB, base);
        System.out.println(divideB);
        System.out.println("===============System Matrix=================");
        List<List<Integer>> systemEquation = TableSystemEquation.getSystemEquation(divideB, base);
        List<List<Integer>> resolve = EquationsSystemResolver.resolve(systemEquation);
        List<List<BigInteger>> resultNotUniqueList = Calculator.decompose(resolve, divideB, pairTableDB, nParam);
        System.out.println(Calculator.getUniqueResolutions(resultNotUniqueList));
    }
}
