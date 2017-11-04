package ru.job4j.collections.frogway;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 01.11.17;
 * @version $Id$
 * @since 0.1
 */
public class Pair {
   private int x;
   private int y;
   private int value;

    public Pair(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    private Pair prev;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Pair(int x, int y, int value, Pair prev) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.prev = prev;
    }

    public Pair getPrev() {
        return prev;

    }

    public void setPrev(Pair prev) {
        this.prev = prev;
    }

    public boolean equalsCords(Pair pair) {
        return this.x == pair.x && this.y == pair.y;
    }
    public boolean minValue (Pair pair) {
        return this.value  > pair.value;
    }
    public void increment() {
        this.value = this.value + 1;
    }
}
