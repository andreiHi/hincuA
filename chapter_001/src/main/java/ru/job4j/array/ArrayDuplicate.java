package ru.job4j.array;

        import java.util.Arrays;

/**
 * RotateArray.
 * @author Hincu Andrei (andreih1981@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicate {
    /**
     * Метод убирает дубликаты.
     * @param array входящий массив с дубликатами;
     * @return массив без дубликатов.
     */
    public String[] remove(String[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[len - 1];
                    len--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, len);
    }
}
