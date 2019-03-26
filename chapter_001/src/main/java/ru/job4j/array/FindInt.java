package ru.job4j.array;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.03.2019.
 * @version $Id$.
 * @since 0.1.
 * Дан массив [2, 7, 5, 4, 6, 1, 0]. Нужно найти, какое число отсутствует. Сортировать нельзя.
 *
 * Дальше задача усложняется:  [2, 7, 4, 6, 1, 0] - теперь нужно найти два числа. Сортировать по-прежнему нельзя.
 */
public class FindInt {
    private static final Logger LOG = LogManager.getLogger(FindInt.class);

    public int find(int[]array) {
        int res = 0;
        boolean[]flag = new boolean[array.length + 1];
        for (final int val : array) {
            flag[val] = true;
        }
        for (int i = 0; i < flag.length; i++) {
            if (!flag[i]) {
                res = i;
                break;
            }
        }
        return res;
    }
}
