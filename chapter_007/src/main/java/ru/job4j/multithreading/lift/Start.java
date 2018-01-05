package ru.job4j.multithreading.lift;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.01.18;
 * @version $Id$
 * @since 0.1
 */
public class Start {
    public static void main(String[] args) {
     Lift lift = new Lift(args);
     ControlPanel panel = new ControlPanel(lift);
        new Thread(panel).start();

    }
}
