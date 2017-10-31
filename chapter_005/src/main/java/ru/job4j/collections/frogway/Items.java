package ru.job4j.collections.frogway;

/**
 * Класс описывает лягушку деревья финиш и ходы лягушки.
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Items {
    private String name;
    private Position position;

    public Items(String name, int y, int x) {
        this.name = name;
        this.position = new Position(y, x);
    }

    public Position firstStep() {
        Position p = null;
        if (position.canMoveToPosition(2, 1)) {
            p = position.moveTo(2, 1);
        }
        return p;
    }
    public Position secondStep() {
        Position p = null;
        if (position.canMoveToPosition(-2, 1)) {
            p = position.moveTo(-2, 1);
        }
        return p;
    }
    public Position thirdStep() {
        Position p = null;
        if (position.canMoveToPosition(1, 2)) {
            p = position.moveTo(1, 2);
        }
        return p;
    }

    public Position fourthStep() {
        Position p = null;
        if (position.canMoveToPosition(-1, 2)) {
            p = position.moveTo(-1, 2);
        }
        return p;
    }
    public Position fifthStep() {
        return position.moveTo(0, 3);
    }

    public Position getPosition() {
        return position;
    }
}
