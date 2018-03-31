package ru.job4j.multithreading.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 31.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Starter {
    public static void main(String[] args) throws InterruptedException {
        ClassWithString classWithString = new ClassWithString();
        AddNumber addNumber = new AddNumber(0, classWithString);
        AddNumber addNumber2 = new AddNumber(1, classWithString);
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.submit(addNumber);
        ex.submit(addNumber2);
        ex.shutdown();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(classWithString.getString());
    }
}
