package ru.job4j.multithreading.jmm;

/**
 * Илюстрация проблем многопоточности..
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 07.11.17;
 * @version $Id$
 * @since 0.1
 */
public class TestThreads {
    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 1000; i++ ) {
            CounterThread t = new CounterThread(counter);
        }
        long l = counter.getCount();
        System.out.println(l);
        // ответы всегда разные и практически никогда не равны 1000000 как ожидается
    }
}
