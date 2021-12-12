package utils;

import java.math.BigInteger;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter @Setter
public class FactorBase {

    private int number;
    private int limit;
    private Set<Integer> base;

    public FactorBase(int limit, int number) {
        this.limit = limit;
        this.number = number;
        generateBase();
    }

    private void generateBase() {
        base = new HashSet<>();
        for (int i = 0; i <= limit; ++i) {
            if (PrimeChecker.isPrime(i) && Legendre.getSymbol(number, i) == 1) {
                base.add(i);
            }
        }
        if (base.contains(0)) {
            base.remove(0);
        }
    }
}
