package cloud.cholewa.sages.ex013_semaphore;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PrintingQueue {

    private final Random random = new Random();

    private final CustomLogger logger = new CustomLogger(this.getClass().getSimpleName(), Thread.currentThread().getName());
    private final Semaphore semaphore;

    public PrintingQueue(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void print(String text) {
        try {
            logger.message(Thread.currentThread().getName() + " : Waiting for permission to print");
            semaphore.acquire();
            logger.message(Thread.currentThread().getName() + " : Permission granted, printing...");

            int nextInt = random.nextInt(10_000);
            logger.message(Thread.currentThread().getName() + " : Wait for " + nextInt + " milliseconds");

            Thread.sleep(nextInt);
            System.out.println(text);
            semaphore.release();
            logger.message(Thread.currentThread().getName() + " : Lock released");
        } catch (InterruptedException e) {
            logger.interruptedDuringWait();
        }
    }
}
