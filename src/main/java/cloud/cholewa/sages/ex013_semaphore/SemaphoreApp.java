package cloud.cholewa.sages.ex013_semaphore;

import java.util.UUID;
import java.util.concurrent.Semaphore;

public class SemaphoreApp {

    private static final int MAX_THREADS = 30;

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);
        PrintingQueue printingQueue = new PrintingQueue(semaphore);

        for (int i = 0; i < MAX_THREADS; i++) {
            new Thread(() -> printingQueue.print(UUID.randomUUID().toString())).start();
        }
    }
}
