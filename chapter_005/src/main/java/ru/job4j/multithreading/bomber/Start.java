package ru.job4j.multithreading.bomber;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 20.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Start {
    private final  ReentrantLock[][] board;
    public static int getRandomInt(int from, int to) {
        return (int) (from + Math.random() * to);
    }
    public static void main(String[] args) {
        Start start = new Start();
        Bomber bomber = new Bomber(start);
        Thread t = new Thread(bomber);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
     Start() {
        int xY = getRandomInt(15, 6);
        board = new ReentrantLock[xY][xY];
        for (int i = 0; i < xY; i++) {
            for (int j = 0; j < xY; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        System.out.println(String.format("Размер поля установлен %d на %d", xY - 1, xY - 1));
    }

    public ReentrantLock[][] getBoard() {
        return board;
    }
}
