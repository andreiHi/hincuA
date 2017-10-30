package ru.job4j.collections.frogway;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public abstract class Item {
    private String name;
    Position position;

    public Item(String name, int y, int x) {
        this.name = name;
        this.position = new Position(y, x);
    }

}
