package cloud.cholewa.basic.lessons.week2.communication_lesson;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.Queue;
import java.util.UUID;

public class WriterTask implements Runnable {

    private CustomLogger logger;
    private Queue<String> buffer;

    public WriterTask(Queue<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        logger = new CustomLogger(WriterTask.class.getSimpleName(), Thread.currentThread().getName());
        logger.startMessage();

        while (true) {
            synchronized (buffer) {
                waitIfBufferIsNotEmpty();
                writeToBuffer();
            }
        }
    }

    private void writeToBuffer() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.message("Has been interrupted");
        }
        buffer.add(UUID.randomUUID().toString());
        logger.message("Writing new data");
        buffer.notify();
    }

    private void waitIfBufferIsNotEmpty() {
        while (!buffer.isEmpty()) {
            try {
                logger.message("Cant write - buffer is full");
                buffer.wait();
            } catch (InterruptedException e) {
                logger.message("Was interrupted");
            }
        }
    }
}
