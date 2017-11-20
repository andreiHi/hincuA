package ru.job4j.multithreading.bomber;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 20.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class BoardCell {
    private ReentrantLock lock = new ReentrantLock();
    private String name;
    private int x;
    private int y;

    public BoardCell() {
    }

    public BoardCell(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
