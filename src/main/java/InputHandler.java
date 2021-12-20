import com.sun.deploy.util.OrderedHashSet;
import eqations.EquationsSystemResolver;

import java.io.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import utils.Calculator;
import utils.FactorBase;
import utils.Legendre;

public class InputHandler {


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter N, B and A values: \n[N] - value: ");
        BigInteger nParam = new BigInteger(reader.readLine());

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
