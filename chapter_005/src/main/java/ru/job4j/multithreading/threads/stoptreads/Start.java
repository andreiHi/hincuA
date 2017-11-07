package ru.job4j.multithreading.threads.stoptreads;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 07.11.17;
 * @version $Id$
 * @since 0.1
 */
public class Start {
    public static void main(String[] args) {
        CountChar countChar = new CountChar();
        Time time = new Time(300);
        Thread tCoutCharter = new Thread(countChar);
       tCoutCharter.start();
        while (true) {
            if (!time.getT().isAlive()) {
                System.out.println("время вышло счет останавливается");
                tCoutCharter.interrupt();
                break;
            }
        }
    }
}
