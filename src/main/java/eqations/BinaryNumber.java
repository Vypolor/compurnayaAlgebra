package eqations;

import java.util.ArrayList;
import java.util.List;

public class BinaryNumber {
    public List<Integer> getNumber() {
        return number;
    }

    private List<Integer> number;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        for (Integer curDigit : number) {
            builder.append(curDigit);
        }
        return builder.toString();
    }

    public BinaryNumber(int bytesCount) {
        number = new ArrayList<>();
        for (int i = 0; i < bytesCount; ++i) {
            number.add(0);
        }
    }


    public void increment() {
        for (int i = number.size()-1; i >= 0; --i) {
            if (number.get(i) == 0) {
                number.set(i, 1);
                return;
            }
            number.set(i, 0);
        }
    }

    public Integer get(int index) {
        return number.get(index);
    }
}
