package cloud.cholewa.basic.communication_lesson;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.Queue;

public class ReaderTask implements Runnable {

    private CustomLogger logger;
    private Queue<String>  buffer;

    public ReaderTask(Queue<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        logger = new CustomLogger(ReaderTask.class.getSimpleName(), Thread.currentThread().getName());
        logger.startMessage();

        while (true) {
            synchronized (buffer) {
                waitIfBufferHasData();
                readFromBuffer();
            }
        }
    }

    private void readFromBuffer() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.message("Has been interrupted");
        }
        String temp = buffer.poll();
        logger.message("Reading: " + temp);
        buffer.notify();
    }

    private void waitIfBufferHasData() {
        while (buffer.isEmpty()) {
            try {
                logger.message("Buffer is empty");
                buffer.wait();
            } catch (InterruptedException e) {
                logger.message("Has been interrupted");
            }
        }
    }
}
