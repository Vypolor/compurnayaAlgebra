import qsalgorithm.QSAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class InputHandler {


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input value [N]: ");
        BigInteger nParam = new BigInteger(reader.readLine());

        System.out.println("Input value [A]: ");
        Integer aParam = Integer.parseInt(reader.readLine());

        reader.close();

        QSAlgorithm.execute(nParam, aParam);
    }
}
