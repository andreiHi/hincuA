package ru.job4j.collections.frogway;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Position {
    private int x;
    private int y;

    public Position(int y, int x) {
        this.x = x;
        this.y = y;
    }
    public Position moveTo(int y, int x) {
        int xX = this.x + x;
        int yY = this.y + y;
        if (xX > 16) {
            xX = xX - 16;
        }
        return new Position(yY, xX);
    }

    public boolean canMoveToPosition(int y, int x) {
        return  ((this.y + y) < 11 && (this.y + y) > 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
