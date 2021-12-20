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
        for (BigInteger i = BigInteger.valueOf(3L); !i.equals(limit.add(BigInteger.ONE)); i = i.add(BigInteger.ONE)) {
            if (i.isProbablePrime(1) && Legendre.getSymbol(number, i) == 1) {
                base.add(i);
            }
        }
        base.remove(BigInteger.ZERO);
    }

}
