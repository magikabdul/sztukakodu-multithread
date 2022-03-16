package cloud.cholewa.basic.lessons.week2.stopping_lesson;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");

        Thread thread = new Thread(new StoppingLesson());
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();

        System.out.println("Done");
    }
}
