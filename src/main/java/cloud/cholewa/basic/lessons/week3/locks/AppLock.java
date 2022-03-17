package cloud.cholewa.basic.lessons.week3.locks;

import cloud.cholewa.basic.helpers.CustomLogger;
import lombok.SneakyThrows;

import java.util.ArrayDeque;
import java.util.Queue;

public class AppLock {

    @SneakyThrows
    public static void main(String[] args) {

        CustomLogger logger = new CustomLogger("Main", Thread.currentThread().getName());
        logger.startMessage();

//        Long value = 0L;
        Queue<Long> value = new ArrayDeque<>();
        value.add(0L);
        Counter counter = new Counter(value);

        Thread t0 = new Thread(counter);
        Thread t1 = new Thread(counter);
        Thread t2 = new Thread(counter);
        Thread t3 = new Thread(counter);

        t0.start();
        t1.start();
        t2.start();
        t3.start();

//        t0.join();
//        t1.join();
//        t2.join();
//        t3.join();
        Thread.sleep(2000);
//        logger.message("From counter: " + counter.getValue());
        logger.message("From main: " + value);


    }
}
