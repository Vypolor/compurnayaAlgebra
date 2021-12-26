package utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

import static utils.BigIntegerConstants.B2;

@Data
@Getter @Setter
public class FactorBase {

    private BigInteger number;
    private BigInteger limit;
    private Set<BigInteger> base;

    public FactorBase(BigInteger limit, BigInteger number) {
        this.limit = limit;
        this.number = new BigInteger(number.toString());
        generateBase();
    }
    private void generateBase() {
        base = new LinkedHashSet<>();
        BigInteger lastProbablePrime = B2;
        base.add(lastProbablePrime);

        for (; lastProbablePrime.compareTo(limit) < 0; lastProbablePrime = lastProbablePrime.nextProbablePrime()) {
            BigInteger candidate = lastProbablePrime.nextProbablePrime();
            if (Legendre.getSymbol(number, candidate) == 1) {
                base.add(candidate);
            }
        }
    }

}
