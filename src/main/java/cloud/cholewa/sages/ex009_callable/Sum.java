package cloud.cholewa.sages.ex009_callable;

import java.util.concurrent.Callable;

public class Sum implements Callable<Integer> {

    private final int firstValue;
    private final int secondValue;

    public Sum(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public Integer call() {
        return firstValue + secondValue;
    }
}
