package ru.job4j.multithreading.bomber;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 20.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Start {
    final ReentrantLock[][] board = new ReentrantLock[10][10];
    public static int getRandomInt() {
        return (int) (Math.random() * 10);
    }
    public static void main(String[] args) {

    }

    class Bomber implements Runnable {


        @Override
        public void run() {
            while (true) {

            }
        }
        public void move () {

        }
    }

}
