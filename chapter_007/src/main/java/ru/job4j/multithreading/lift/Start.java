package ru.job4j.multithreading.lift;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Запуск программы.
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.01.18;
 * @version $Id$
 * @since 0.1
 */
public class Start {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer>queue = new ArrayBlockingQueue<Integer>(5);
     Lift lift = new Lift(args, queue);
     ControlPanel panel = new ControlPanel(lift, queue);
     new Thread(lift).start();
     new Thread(panel).start();

    }

}
