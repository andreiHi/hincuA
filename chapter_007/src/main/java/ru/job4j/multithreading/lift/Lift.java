package ru.job4j.multithreading.lift;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Класс лифт.
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
@ThreadSafe
public class Lift implements Runnable {
    private ArrayBlockingQueue<Integer> queue;
    private int level;
    private int heightLevel;
    private int speed;
    private int waitingTime;
    private String[]levels;
    private int currentPosition;
    private int levelNeed;

    public int getLevel() {
        return level;
    }

    public Lift(String[]param, ArrayBlockingQueue<Integer> q) {
        this.queue = q;
        this.level       = Integer.parseInt(param[0]);
        this.heightLevel = Integer.parseInt(param[1]);
        this.speed       = Integer.parseInt(param[2]);
        this.waitingTime = Integer.parseInt(param[3]);
        init(level);
    }

    @Override
    public void run() {
        while (true) {
//            synchronized (this) {
//                try {
//                    this.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            try {
                int i = queue.take();
                move(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //move(levelNeed);
        }

    }

    /**
     * Метод для управления дверьми лифта.
     * @param waitingTime время ожидания между открытием и закрытием дверей.
     */
    public void openClose(int waitingTime) {
        try {
            synchronized (this) {
                System.out.println("Лифт открыл двери.");
                TimeUnit.SECONDS.sleep(waitingTime);
                System.out.println("Лифт закрыл двери.");
                this.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Медод инициализирует этажи здания в соответствии с полученными параметрами.
     * @param level колличество этажей.
     */
    public void init(int level) {
        this.levels = new String[level];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = String.format("Лифт проежает этаж %d", i + 1);
        }

    }

    /**
     * Метод производит перемещение лифта в заданном направлении.
     * @param rs требуемый этаж.
     */
    public void move(int rs) {
        try {
            rs = rs - 1;
            if (rs == currentPosition) {
                openClose(waitingTime);
            } else {
                if (rs > currentPosition) {
                    for (int i = currentPosition; i < rs; i++) {
                        TimeUnit.SECONDS.sleep(heightLevel / speed);
                        System.out.println(levels[i]);
                    }
                    openClose(waitingTime);
                    this.currentPosition = rs;
                } else {
                    for (int i = currentPosition; i > rs; i--) {
                        System.out.println(levels[i]);
                        TimeUnit.SECONDS.sleep(heightLevel / speed);
                    }
                    openClose(waitingTime);
                    this.currentPosition = rs;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setLevelNeed(int levelNeed) {
        this.levelNeed = levelNeed;
    }
}
