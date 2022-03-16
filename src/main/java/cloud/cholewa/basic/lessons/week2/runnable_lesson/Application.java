package cloud.cholewa.basic.lessons.week2.runnable_lesson;

public class Application {

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableLesson());
        thread.start();
    }
}
