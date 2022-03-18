package cloud.cholewa.sages.ex014_count_down_latch;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.concurrent.CountDownLatch;

public class Meeting implements Runnable {

    private final CustomLogger logger = new CustomLogger(this.getClass().getSimpleName(), Thread.currentThread().getName());
    private final CountDownLatch latch;

    public Meeting(CountDownLatch latch) {
        this.latch = latch;
    }

    public void addParticipant(String name) {
        latch.countDown();
        logger.message(name + " has join to meeting");
    }

    @Override
    public void run() {
        logger.message("Waiting for participants");
        try {
            latch.await();
            logger.message("Meeting is starting...");
        } catch (InterruptedException e) {
            logger.interruptedDuringWait();
        }
    }
}
