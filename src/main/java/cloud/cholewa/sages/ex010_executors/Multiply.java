package cloud.cholewa.sages.ex010_executors;

import java.util.concurrent.Callable;

public class Multiply implements Callable<Integer> {

    private final int firstValue;
    private final int secondValue;

    public Multiply(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public Integer call() {
        return firstValue * secondValue;
    }
}
