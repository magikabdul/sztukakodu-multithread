package cloud.cholewa.basic.helpers;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

public class CustomLogger {

    private final int MAX_BLOCK_LENGTH = 30;

    private final String className;
    private final String threadName;


    public CustomLogger(String className, String threadName) {
        this.className = className;
        this.threadName = threadName;
    }

    public void startMessage() {
        System.out.println(header() + "Starting...");
    }

    public void endMessage() {
        System.out.println(header() + "Done!");
    }

    public void interruptedOnEnd() {
        System.out.println(header() + "Has been interrupted - on the end of run method");
    }

    public void interruptedDuringWait() {
        System.out.println(header() + "Has been interrupted - in WAIT state");
    }

    public void message(String message) {
        System.out.println(header() + message);
    }

    private String header() {
        return " " + getTime() + getBlock("Class", className) + getBlock("Thread", threadName) + "- ";
    }

    private String getBlock(String title, String value) {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ")
                .append(title)
                .append(": ")
                .append(value);

        while (sb.length() < MAX_BLOCK_LENGTH) {
            sb.append(" ");
        }

        sb.append(" ] ");
        return sb.toString();
    }

    private String getTime() {
        StringBuilder sb = new StringBuilder();
        sb.append(Timestamp.from(Instant.now()));

        while (sb.length() < MAX_BLOCK_LENGTH) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
