package cloud.cholewa.sages.ex014_count_down_latch;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownApp {

    public static void main(String[] args) {

        Random random = new Random();
        CountDownLatch latch = new CountDownLatch(5);
        Meeting meeting = new Meeting(latch);

        new Thread(meeting).start();
        meeting.addParticipant("Frank");
        customWait(random);
        meeting.addParticipant("Julia");
        customWait(random);
        meeting.addParticipant("Helen");
        customWait(random);
        meeting.addParticipant("Andy");
        customWait(random);
        meeting.addParticipant("Ann");
    }

    @SneakyThrows
    private static void customWait(Random random) {
        Thread.sleep(random.nextInt(4_000));
    }
}
