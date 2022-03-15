package cloud.cholewa.basic.homeworks.week2;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.Queue;

public class Reader implements Runnable {

    private CustomLogger logger;
    private final Queue<Integer> number;

    public Reader(Queue<Integer> number) {
        this.number = number;
    }

    @Override
    public void run() {
        logger = new CustomLogger(this.getClass().getSimpleName(), Thread.currentThread().getName());
        logger.startMessage();

        synchronized (number) {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    checkIfBufferIsEmpty();
                    countSum();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                logger.interruptedOnEnd();
            }
        }

        logger.endMessage();
    }

    private void countSum() {
        int sum = 0;
        int range = number.isEmpty() ? 0 : number.poll();
        for (int i = 0; i < range; i++) {
            sum += i;
        }
        logger.message("Sum of: " + range + " is: " + sum);
        number.notifyAll();
    }

    private void checkIfBufferIsEmpty() {
        while (number.isEmpty()) {
            try {
                logger.message("Cant read data - buffer is empty");
                number.wait();
            } catch (InterruptedException e) {
                logger.interruptedDuringWait();
            }
        }
    }
}
