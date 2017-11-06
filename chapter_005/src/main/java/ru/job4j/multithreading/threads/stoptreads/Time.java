package ru.job4j.multithreading.threads.stoptreads;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 06.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Time implements Runnable {
    private int time;

    public Time(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        long timeMillis = System.currentTimeMillis();
        while (true) {
            timeMillis = System.currentTimeMillis() - timeMillis;
            if (timeMillis > time) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
