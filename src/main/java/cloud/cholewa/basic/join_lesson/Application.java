package cloud.cholewa.basic.join_lesson;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");

        Thread thread = new Thread(new JoinLesson());
        thread.start();
        thread.join();

        System.out.println("Done");
    }
}
