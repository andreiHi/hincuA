package ru.job4j.array;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.09.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class MatrixCheck {
    /**
     * @param data матрица для проверки диагоналей.
     * @return все диагонали содержат одинаковые элементы.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 1; i < data.length; i++) {
            if (data[0][0] != data[i][i] || data[0][data.length - 1] != data[data.length - 1 - i][i]) {
                result = false;
                break;
            }
        }
        add();
        return result;
    }
    private static void add() {

    }
    /**
     *
     * @param data массив где проверяем диагонали.
     * @param d какую диагональ проверяем (левую или правую).
     * @return совпадают ли элементы в проверяемой диагонали.
     */
    public boolean check(boolean[][] data, char d) {
        boolean result = true;
        if (d == 'l') {
            for (int i = 1; i < data.length; i++) {
                if (data[0][0] != data[i][i]) {
                    result = false;
                }
            }
        } else if (d == 'r') {
            for (int i = 1; i < data.length; i++) {
                if (data[0][data.length - 1] != data[data.length - 1 - i][i]) {
                    result = false;
                }
            }
        }

        return result;
    }
}
