package cloud.cholewa.basic.thread_lesson;

public class ThreadLesson {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " of main");

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("inside thread");
                System.out.println(Thread.currentThread().getName() + " of new thread");
            }
        };
        thread.start();
    }
}
