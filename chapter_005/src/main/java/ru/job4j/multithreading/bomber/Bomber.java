package ru.job4j.multithreading.bomber;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 20.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public    class Bomber implements Runnable {
    Start start;
    ReentrantLock lock;
    int x;
    int y;

    public Bomber(Start start) {
        this.start = start;
        boolean getLock = false;
        do {
            this.x = Start.getRandomInt(0, start.board.length);
            this.y = Start.getRandomInt(0, start.board.length);
            this.lock = start.board[y][x];
            getLock = this.lock.tryLock();
        }while (!getLock);
    }

    @Override
    public void run() {
        while (true) {

        }
    }
    public void move () {

    }
}