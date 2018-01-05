package ru.job4j.multithreading.lift;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.01.18;
 * @version $Id$
 * @since 0.1
 */
public class ControlPanel implements Runnable {
    private Lift lift;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String floorCall = "Введите этаж на котором человек вызывает лифт: ";
    private String callTarget = "Введите этаж на который необходимо переместиться: ";
    public ControlPanel(Lift lift) {
        this.lift = lift;
    }

    @Override
    public void run() {
        synchronized (lift) {
            try {
                while (true) {
                    int rs = askPerson(floorCall);
                    lift.notify();
                    lift.wait();
                    rs = askPerson(callTarget);
                    lift.notifyAll();
                    lift.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public int askPerson(String s) {
        int level;
        System.out.println(s);
        while (true) {
            try {
                level = Integer.parseInt(reader.readLine());
                if (level > 0 && level <= lift.getLevel()) {
                    break;
                } else {
                    System.out.println("Данного этажа нет в этом доме повторите ввод :");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return level;
    }
}
