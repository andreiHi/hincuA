package ru.job4j.collections.frogway;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 01.11.17;
 * @version $Id$
 * @since 0.1
 */
public class Pair {
    int x;
    int y;
    int value;

    public boolean equalsCords(Pair pair) {
        return this.x == pair.x && this.y == pair.y;
    }
    public boolean minValue (Pair pair) {
        return this.value  > pair.value;
    }
}
