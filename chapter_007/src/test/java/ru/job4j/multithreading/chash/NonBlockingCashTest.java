package ru.job4j.multithreading.chash;

import org.junit.Test;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 12.12.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class NonBlockingCashTest {
    private final NonBlockingCash cash = new NonBlockingCash();

    @Test()
    public void name() {
        User base = new User("ivan", 1);
        cash.add(base);

        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                User value = cash.getValue(1);
                User b = new User(value.getName() + i, 1);
                b.setVersion(value.getVersion());
                cash.update(b);
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread.start();
        thread2.start();
        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}