package cloud.cholewa.basic.lessons.week2.stopping_lesson;

public class StoppingLesson implements Runnable {

    @Override
    public void run() {
        int counter = 0;

        try {
            while (!Thread.currentThread().isInterrupted()) {
                counter++;
                System.out.println("Inside stopping lesson thread: " + counter);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Stopped thread in stopping lesson");
        }
    }
}
