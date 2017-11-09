package ru.job4j.multithreading.threads.stoptreads;

/**
 * Start.
 * @author Hincu Andrei (andreih1981@gmail.com) by 07.11.17;
 * @version $Id$
 * @since 0.1
 */
public class Start {
    public static void main(String[] args) {
        int totalTime = 1000;
        CountChar countChar = new CountChar();
        Time time = new Time(totalTime);
        time.start();
        Thread charterCountThread = new Thread(countChar);
        charterCountThread.start();

        while (time.getT().isAlive()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        charterCountThread.interrupt();
        System.out.println("Программа завершает работу.");
    }
}
