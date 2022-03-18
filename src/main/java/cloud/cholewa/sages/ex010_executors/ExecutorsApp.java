package cloud.cholewa.sages.ex010_executors;

import cloud.cholewa.basic.helpers.CustomLogger;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsApp {

    @SneakyThrows
    public static void main(String[] args) {

        CustomLogger logger = new CustomLogger("Main", Thread.currentThread().getName());

        FutureTask<Integer> sumFutureTask = new FutureTask<>(new Sum(3, 9));
        FutureTask<Integer> multiplyFutureTask = new FutureTask<>(new Multiply(3, 9));

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(sumFutureTask);
        logger.message("ExecutorService sum result: " + sumFutureTask.get());
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.schedule(multiplyFutureTask, 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        logger.message("ScheduledExecutorService multiply result: " + multiplyFutureTask.get());
        scheduledExecutorService.shutdown();
    }
}
