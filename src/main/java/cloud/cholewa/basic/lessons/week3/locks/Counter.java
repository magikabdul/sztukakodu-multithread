package cloud.cholewa.basic.lessons.week3.locks;

import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter implements Runnable {

    private Queue<Long> value;
    private Lock lock = new ReentrantLock();

    public Counter(Queue<Long> value) {
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            lock.lock();
            increment();
            lock.unlock();
        }
    }

    private void increment() {
        if (!value.isEmpty()) {
            long temp = value.poll();
            value.add(++temp);
        }
    }

//    public long getValue() {
//        return value;
//    }
}
