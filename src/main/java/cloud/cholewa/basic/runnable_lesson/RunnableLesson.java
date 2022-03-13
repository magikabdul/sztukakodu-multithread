package cloud.cholewa.basic.runnable_lesson;

public class RunnableLesson implements Runnable {

    @Override
    public void run() {
        System.out.println("I inside runnable " + Thread.currentThread().getName());
    }
}
