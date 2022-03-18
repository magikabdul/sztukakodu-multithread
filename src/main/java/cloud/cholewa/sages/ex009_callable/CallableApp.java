package cloud.cholewa.sages.ex009_callable;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CustomLogger logger = new CustomLogger("Main", Thread.currentThread().getName());

        Sum sum = new Sum(2, 67);

        FutureTask<Integer> futureTask = new FutureTask<>(sum);
        new Thread(futureTask).start();

        logger.message("Result of sum is: " + futureTask.get());
    }
}
