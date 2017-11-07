package ru.job4j.multithreading.threads.stoptreads;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 06.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Time implements Runnable {
    private int time;
    private Thread t;

    public Time(int time) {
        this.time = time;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Начало работы time");
        long timeMillis = System.currentTimeMillis();
        while (true) {
            long t = System.currentTimeMillis();
            if (t - timeMillis >= time) {
                break;
            }
        }
        System.out.println("завершение time");
    }

    public Thread getT() {
        return t;
    }
}
