package ru.job4j.multithreading.lift;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

    public int getLevel() {
        return level;
    }

    public Lift(String...param) {
        this.level       = Integer.parseInt(param[0]);
        this.heightLevel = Integer.parseInt(param[1]);
        this.speed       = Integer.parseInt(param[2]);
        this.waitingTime = Integer.parseInt(param[3]);
    }

    @Override
    public void run() {

    }
}
