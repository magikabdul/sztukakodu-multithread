package cloud.cholewa.basic.homeworks.week2;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.Queue;
import java.util.Random;

public class RandomGeneratorLarge implements Runnable {

    private CustomLogger logger;

    private final Queue<Integer> number;
    private final Random random = new Random();

    public RandomGeneratorLarge(Queue<Integer> number) {
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
                    generateNumber();
                    Thread.sleep(1_500);
                }
            } catch (InterruptedException e) {
                logger.interruptedOnEnd();
            }
        }
    }

    private void generateNumber() {
        number.add(random.nextInt(400) + 100);
        logger.message("Generated number: " + number);
        number.notify();
    }

    private void checkIfBufferIsEmpty() {
        while (!number.isEmpty()) {
            try {
                logger.message("Cant generate new number - buffer is full");
                number.wait();
            } catch (InterruptedException e) {
                logger.interruptedDuringWait();
            }
        }
    }
}
