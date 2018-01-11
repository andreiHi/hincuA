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
        try {
            if (args.length < 4) {
                throw new Exception("Введены не все данные для старта");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        //очередь для запросов из подьезда
        ArrayBlockingQueue<Integer> ext = new ArrayBlockingQueue<Integer>(5);
        //очередь для запросов из лифта
        ArrayBlockingQueue<Integer> insaid = new ArrayBlockingQueue<Integer>(5);
        Lift lift = new Lift(args[0], args[1], args[2], args[3], ext, insaid);

        new Thread(lift).start();


    }

}
