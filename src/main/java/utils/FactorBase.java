package utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
        base = new HashSet<>();
        base.add(BigInteger.valueOf(2));
        for (BigInteger i = BigInteger.valueOf(3L); !i.equals(limit.add(BigInteger.ONE)); i = i.add(BigInteger.valueOf(2L))) {
            if (i.isProbablePrime((int)Math.log(i.doubleValue())) && Legendre.getSymbol(number, i) == 1) {
                base.add(i);
            }
        }
        if (base.contains(0)) {
            base.remove(0);
        }
    }

    public static boolean isPrime(BigInteger num) {
        BigInteger sqrtNum = Calculator.sqrtN(num);
        for (BigInteger i = BigInteger.valueOf(3L); i.compareTo(sqrtNum) < 1; i = i.add(BigInteger.valueOf(2L))) {
            if (num.remainder(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }

}
