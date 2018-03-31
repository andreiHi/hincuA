package ru.job4j.multithreading.lock;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 31.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ClassWithString {

    private StringBuilder sb = new StringBuilder("string");

    public void addInt(int i) {
        this.sb.append(i);
    }
    public String getString() {
        return sb.toString();
    }
}
