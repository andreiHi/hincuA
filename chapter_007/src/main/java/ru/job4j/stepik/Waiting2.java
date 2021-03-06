package ru.job4j.stepik;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Waiting2 {
    private static final  int POOL_SIZE = 50;
    public static void main(final String[] args) throws Exception {
        final ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
        final CountDownLatch ready = new CountDownLatch(POOL_SIZE);
        final AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < POOL_SIZE; i++) {
            executor.submit(() -> {
                counter.incrementAndGet();
                System.out.printf("Thread %s is completed%n", Thread.currentThread().getName());
                ready.countDown();
            });
        }
        ready.await();
        System.out.println(counter.get());
        executor.shutdown();
    }
}
