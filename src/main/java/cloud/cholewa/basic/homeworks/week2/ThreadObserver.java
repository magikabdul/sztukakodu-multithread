package cloud.cholewa.basic.homeworks.week2;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.List;
import java.util.Queue;

public class ThreadObserver implements Runnable {

    private CustomLogger logger;

    private final List<Thread> threadList;
    private final Queue<Integer> number;

    public ThreadObserver(List<Thread> threadList, Queue<Integer> number) {
        this.threadList = threadList;
        this.number = number;
    }

    @Override
    public void run() {
        logger = new CustomLogger(this.getClass().getSimpleName(), Thread.currentThread().getName());
        logger.startMessage();

        synchronized (number) {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(1000);
                    reportThreadsStatus();
                }
            } catch (InterruptedException e) {
                logger.interruptedOnEnd();
            }
        }

        logger.endMessage();
    }

    private void reportThreadsStatus() {
        logger.message("Number: " + number.peek());
        for (Thread thread : threadList)
            logger.message(
                    thread.getClass().getSimpleName() +
                            " " +
                            thread.getState().name()
            );
        number.notifyAll();
        try {
            number.wait();
        } catch (InterruptedException e) {
            logger.interruptedDuringWait();
        }
    }
}
