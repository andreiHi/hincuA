package ru.job4j.tictactoe;

import javafx.scene.shape.Rectangle;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 05.09.2018.
 * @version $Id$.
 * @since 0.1.
 */

public class Figure3T extends Rectangle {
    private boolean markX = false;
    private boolean markO = false;

    public Figure3T() {
    }

    public Figure3T(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    public void take(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    public boolean hasMarkX() {
        return this.markX;
    }

    public boolean hasMarkO() {
        return this.markO;
    }

    public boolean hasMark(boolean value) {
        return this.markX != this.markO && value ? this.markX : this.markO;
    }
}