package ru.job4j.multithreading.lift;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 05.01.18;
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class ControlPanel implements Runnable {
    @GuardedBy("this")
    private final Lift lift;
    private ArrayBlockingQueue<Integer> queue;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String levelCall = "Введите этаж на котором человек вызывает лифт: ";
    private String callTarget = "Введите этаж на который необходимо переместиться: ";

    public ControlPanel(Lift lift, ArrayBlockingQueue<Integer> queue) {
        this.lift = lift;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            changeLevel(levelCall);
            changeLevel(callTarget);
        }
    }

    /**
     * Метод для обшения с пользователем.
     * @param s вопрос пользователю.
     * @return ответ пользователя.
     */
    public int askPerson(String s) {
        int level = 0;
        System.out.println(s);
        while (true) {
            try {
                String line = reader.readLine();
                try {
                    level = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.out.println("Введенные данные не коректны, повторите ввод: ");
                    continue;
                }
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

    /**
     * Метод для передачи данных лифту для исполнения.
     * @param s вопрос для пользователя.
     */
    public void changeLevel(String s) {
        synchronized (lift) {
            int rs = askPerson(s);
            lift.setLevelNeed(rs);
            lift.notify();
            try {
                lift.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
