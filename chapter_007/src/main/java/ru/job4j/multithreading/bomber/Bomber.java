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
    private final String name;
    protected ReentrantLock forLock;
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

    private int timeForTryLock;

    public Bomber(Start start, String name) {
        this.start = start;
        this.name = name;
        if (name.equalsIgnoreCase("bomber")) {
            this.timeForTryLock = 500;
        } else {
            this.timeForTryLock = 5000;
        }
    }


    @Override
    public void run() {
        boolean getLock;
        do {
            //Получаем случайные координаты х и у.
            this.x = start.getRandomInt(0, start.getBoard().length);
            this.y = start.getRandomInt(0, start.getBoard().length);
            //получаем замок из этих координат
            this.lock = start.getBoard()[y][x];
            //проверяем можем ли мы го захватить
            getLock = this.lock.tryLock();
            //если не можем цикл повторяется , если лок захвачен то бомбер родился в указанных координатах
        } while (!getLock);
        System.out.println(String.format("%s появился по координатам : %d : %d ", name, y, x));
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //засыпаем на секунду т.к бомбер может ходить раз в секунду
                TimeUnit.SECONDS.sleep(1);
                //ходим
                move();
                System.out.println(String.format("%s ходит в %d : %d", name, y, x));
            } catch (InterruptedException e) {
                System.out.println("Поток прерван");
            }
        }
    }

    /**
     * Метод рендомно принимает решение куда ходить.
     */
    public void move() {
        //получаем случайное число от 0 до 3 включительно
        int number = start.getRandomInt(0, 4);
        if (number == 0) { //идем на лево
            move(-1, 0);

        } else if (number == 1) { //идем вниз
            move(0, 1);

        } else if (number == 2) { //идем направо
            move(1, 0);

        } else if (number == 3) { //идем вверх
            move(0, -1);
        }
    }

    /**
     * Метод пробует перейти в заданную ячейку, если это край карты то запрос перехода.
     * @param xX направление по оси х.
     * @param yY направление по оси у.
     */
    public void move(int xX, int yY) {
        //получаем координаты куда хотим пойти
        int x = this.x + xX;
        int y = this.y + yY;
        System.out.println(String.format("%s хочет пойти в %d : %d", name, y, x));
        //проверяем не вылазием ли мы за пределы поля
        if (!checkBorders(x, y)) {
            System.out.println("Сюда нельзя");
            //еслы вылазием производим перезапрос направления хода
            move();
        } else {
            //сохраняем замок из точки куда хотим пойти в переменную lock
            forLock = start.getBoard()[y][x];
            if (!lock.tryLock()) {
                Thread bomber;
//                if (forLock.getOwner()) {
//
//                }
            }
            try {
                //пытаемся захватить замок в течении timeForTryLock млс
                boolean get = lock.tryLock(timeForTryLock, TimeUnit.MILLISECONDS);
                if (get) {
                    //если захватили отпускаем старый замок перезаписываем в переменную класа свежезахваченный замок
                    this.lock.unlock();
                    this.lock = lock;
                    //устонавливаем новые координаты бомбера
                    this.x = x;
                    this.y = y;
                } else {
                    //если замок не захватился делаем повторный запрос хода
                    System.out.println(String.format("Ячейка занята %s идет другую сторону",this.name));
                    move();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
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