package ru.job4j.multithreading.threadpool;

import java.util.LinkedList;

/**
 * ThreadPool.
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class ThreadPoolEx {
    /**
     * колличество процессоров.
     */
    private final int core;
    /**
     * Очередь на выполнение задачь.
     */
    private final LinkedList<Runnable> list;
    /**
     * Массив нитей которые могут одновременно работать.
     * в нутри них запустятся нити из очереди , если очередь пуста они ждут добавления.
     */
    private final InnerThreads[] threads;
    /**
     * Завершение работы.
     */
    private boolean shutDown = false;

    public ThreadPoolEx() {
        this.core = Runtime.getRuntime().availableProcessors();
        this.list = new LinkedList<>();
        this.threads = new InnerThreads[core];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new InnerThreads();
            threads[i].start();
        }
    }
    public void shutDown() {
        this.shutDown = true;
    }
    public void add(Runnable work) {
        synchronized (list) {
            list.addLast(work);
            list.notify();
        }
    }

    public boolean isShutDown() {
        return shutDown;
    }

    private class InnerThreads extends Thread {
        @Override
        public void run() {
            Runnable runnable;
            while (true) {
                synchronized (list) {
                    while (list.isEmpty()) {
                        try {
                            if (isShutDown()) {
                                break;
                            }
                            list.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    runnable = list.poll();
                    if (runnable != null) {
                        runnable.run();
                    }
                    if (isShutDown() && list.isEmpty()) {
                        break;
                    }
                }
            }
        }

    }
}

/**
 * Запуск импрвизированного пула.
 */
class TestThreadPool {
    public static void main(String[] args) {
        ThreadPoolEx ex = new ThreadPoolEx();
        for (int i = 1; i < 11; i++) {
            ex.add(new Work("Thread № " + i));
        }
        ex.shutDown();
        /**
         * показывает что майн завершил работу и выполняются потоки из очереди.
         */
        System.out.println("Майн отработал");
    }
}

/**
 * Класс для создания работы в пуле.
 */
class Work implements Runnable {
    private final String name;

    public Work(final String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(String.format("Запущен поток : %s", name));
        try {
            System.out.println(name + " работает.");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " завершил работу.");
    }
}