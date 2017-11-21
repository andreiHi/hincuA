package ru.job4j.multithreading.bomber;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 20.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public  class Bomber implements Runnable {
    /**
     * Имя.
     */
    private final String name = "Бомбер";
    /**
     * доступ к полю.
     */
    private Start start;
    /**
     * Замок.
     */
    private ReentrantLock lock;
    /**
     * координата х в массиве.
     */
    private int x;
    /**
     * координата у в массиве.
     */
    private int y;

    public Bomber(Start start) {
        this.start = start;
    }

    public Bomber() {
    }

    @Override
    public void run() {
        boolean getLock;
        do {
            this.x = Start.getRandomInt(0, start.getBoard().length);
            this.y = Start.getRandomInt(0, start.getBoard().length);
            this.lock = start.getBoard()[y][x];
            getLock = this.lock.tryLock();
        } while (!getLock);
        System.out.println(String.format("%s появился по координатам : %d : %d ", name, y, x));
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                move();
                System.out.println(String.format("%s ходит в %d : %d", name, y, x));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод рендомно принимает решение куда ходить.
     */
    public void move() {
        int number = Start.getRandomInt(0, 4);
        switch (number) {
            case 0 : move(-1, 0);
            break;
            case 1 : move(0, 1);
            break;
            case 2 : move(1, 0);
            break;
            case 3 : move(0, -1);
        }
    }

    /**
     * Метод пробует перейти в заданную ячейку, если это край карты то запрос перехода.
     * @param xX направление по оси х.
     * @param yY направление по оси у.
     */
    public void move(int xX, int yY) {
        int x = this.x + xX;
        int y = this.y + yY;
        System.out.println(String.format("%s хочет пойти в %d : %d", name, y, x));
        if (!checkBorders(x, y)) {
            System.out.println("Сюда нельзя");
            move();
        } else {
            ReentrantLock lock = start.getBoard()[y][x];
            try {
                boolean get = lock.tryLock(500, TimeUnit.MILLISECONDS);
                if (get) {
                    this.lock.unlock();
                    this.lock = lock;
                    this.x = x;
                    this.y = y;
                } else {
                    move();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод проверяет не находятся ли переданные координаты за пределами поля.
     * @param x координата х.
     * @param y координата у.
     * @return да или нет.
     */
    public boolean checkBorders(int x, int y) {
        boolean check = true;
        if (x >= start.getBoard().length || y >= start.getBoard().length) {
            check = false;
        }
        if (x < 0 || y < 0) {
            check = false;
        }
        return check;
    }
}