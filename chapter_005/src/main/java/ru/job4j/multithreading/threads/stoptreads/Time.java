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
    }
    public void start() {
        t.start();
    }
    @Override
    public void run() {
        System.out.println("Начало работы программы Time");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Завершение программы Time");
    }

    public Thread getT() {
        return t;
    }
}
