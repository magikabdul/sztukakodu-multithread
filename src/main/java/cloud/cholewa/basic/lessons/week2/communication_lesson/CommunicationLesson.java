package cloud.cholewa.basic.lessons.week2.communication_lesson;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.ArrayDeque;
import java.util.Queue;

public class CommunicationLesson {

    public static void main(String[] args) {
        CustomLogger logger = new CustomLogger("Main", Thread.currentThread().getName());

        logger.startMessage();

        Queue<String> buffer = new ArrayDeque<>();

        Thread t1 = new Thread(new WriterTask(buffer));
        t1.start();

        Thread t0 = new Thread(new ReaderTask(buffer));
        t0.start();

        logger.endMessage();
    }
}
