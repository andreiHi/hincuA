package ru.job4j.multithreading.lift;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Lift implements Runnable {
    private static final Logger LOG = LogManager.getLogger(Lift.class);
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

    public Lift(String...param) {
        this.level       = Integer.parseInt(param[0]);
        this.heightLevel = Integer.parseInt(param[1]);
        this.speed       = Integer.parseInt(param[2]);
        this.waitingTime = Integer.parseInt(param[3]);
        init(level);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            move(levelNeed);
        }

    }
    public void openClose(int waitingTime) {
        try {
            synchronized (this) {
                System.out.println("Лифт открыл двери.");
                //this.notifyAll();
            }
            TimeUnit.SECONDS.sleep(waitingTime);
            System.out.println("Лифт закрыл двери.");

            synchronized (this) {
                this.notifyAll();
               // this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void init(int level) {
        this.levels = new String[level];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = String.format("Проежаем этаж %d", i + 1);
        }

    }

    public void move(int rs) {
        if (rs - 1  == currentPosition) {
            openClose(waitingTime);
        } else {
            if (rs -1 > currentPosition) {
                int count = 0;
                for (int i = currentPosition + 1; i < rs - 1; i++) {
                    try {
                        count++;
                        TimeUnit.SECONDS.sleep(heightLevel / speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(count);
                    System.out.println(levels[i]);
                }
                openClose(waitingTime);
                this.currentPosition = rs - 1;
            }
        }
    }

    public void setLevelNeed(int levelNeed) {
        this.levelNeed = levelNeed;
    }
}
