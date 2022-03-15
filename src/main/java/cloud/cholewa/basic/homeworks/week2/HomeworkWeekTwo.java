package cloud.cholewa.basic.homeworks.week2;

import cloud.cholewa.basic.helpers.CustomLogger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class HomeworkWeekTwo {

    public static void main(String[] args) {

        CustomLogger logger = new CustomLogger("Main", Thread.currentThread().getName());
        Queue<Integer> number = new ArrayDeque<>();

        RandomGeneratorSmall generatorSmall = new RandomGeneratorSmall(number);
        Thread t0 = new Thread(generatorSmall);
        RandomGeneratorLarge generatorLarge = new RandomGeneratorLarge(number);
        Thread t1 = new Thread(generatorLarge);
        Reader reader = new Reader(number);
        Thread t2 = new Thread(reader);

        ThreadObserver observer = new ThreadObserver(List.of(t0, t1, t2), number);
        Thread t3 = new Thread(observer);

        logger.startMessage();

        t3.start();
        t0.start();
        t1.start();
        t2.start();

        try {
            Thread.sleep(30_000);
        } catch (InterruptedException e) {
            logger.message("Interrupted");
        }

        t0.interrupt();
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();

        logger.endMessage();
    }
}
