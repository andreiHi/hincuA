package ru.job4j.multithreading.lock;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 31.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AddNumber implements Runnable {
    private final ClassWithString classWithString;
    private int i;

    public AddNumber(int i, ClassWithString classWithString) {
        this.classWithString = classWithString;
        this.i = i;
    }
    @Override
    public void run() {
        int j = 50;
        while (j != 0) {
            j--;
            synchronized (classWithString) {
                for (int index = 0; index < 10; index++) {
                    classWithString.addInt(i);
                }
                classWithString.notifyAll();
                try {
                    classWithString.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
