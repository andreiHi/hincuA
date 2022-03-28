package ru.job4j.pecs;

import java.util.Objects;

public class Class0 implements Runnable {
    private final Object o;

    public Class0(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        synchronized (o) {
            try {
                System.out.println("wait");
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("exit Class0");
    }

    public static void main(String[] args) throws InterruptedException {
        final Object o = new Object();
        Class0 c = new Class0(o);
        final Thread thread = new Thread(c);
        thread.start();
        Thread.sleep(5000);
        final Thread thread1 = new Thread(new T(o));
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println("finish");
    }
}

class T implements Runnable {
    private final Object o;

    public T(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        synchronized (o) {
            o.notifyAll();
            System.out.println("notifay called");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exit notifay class");
        }
    }
}
