package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        return this.checkColumnsAndRows(true) || this.checkDiagonals(true);
    }

    public boolean isWinnerO() {
        return this.checkColumnsAndRows(false) || this.checkDiagonals(false);
    }

    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < this.table.length && !result; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (!this.table[i][j].hasMarkX() && !this.table[i][j].hasMarkO()) {
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean checkColumnsAndRows(boolean value) {
        boolean resultOuter = false;
        for (int i = 0; i < this.table.length && !resultOuter; i++) {
            boolean resultInner = true;
            for (int j = 0; j < this.table.length && resultInner; j++) {
                if (!this.table[j][i].hasMark(value) && !this.table[i][j].hasMark(value)) {
                    resultInner = false;
                }
            }
            resultOuter = resultInner;
        }
        return resultOuter;
    }

    private boolean checkDiagonals(boolean value) {
        boolean result = true;
        for (int i = 0; i < this.table.length && result; i++) {
            if (!this.table[i][i].hasMark(value) && !this.table[i][this.table.length - 1 - i].hasMark(value)) {
                result = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}