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

    private BigDecimal number;
    private BigInteger limit;
    private Set<BigInteger> base;

    public FactorBase(BigInteger limit, BigInteger number) {
        this.limit = limit;
        this.number = new BigDecimal(number.toString());
        generateBase();
    }

    private void generateBase() {
        base = new HashSet<>();
        for (BigInteger i = BigInteger.ZERO; !i.equals(limit); i = i.add(BigInteger.ONE)) {
            BigDecimal iB = new BigDecimal(i.toString());
            if (i.isProbablePrime(1) && Legendre.getSymbol(number, iB) == 1) {
                base.add(new BigInteger(iB.toString()));
            }
        }
        if (base.contains(0)) {
            base.remove(0);
        }
    }
}
