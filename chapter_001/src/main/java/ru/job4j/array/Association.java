package ru.job4j.array;
/**
 * Association.
 *
 * @author Hincu Andrei (andreih1981@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Association {
    /**
     * Метод обьединяет массивы в один.
     * @param first - первый отсортированный массив.
     * @param second - второй отсортированный массив.
     * @return - обьединенный масив.
     */
    public int[] assotiationSortedArray(int[] first, int[] second) {
        int[] array = new int[first.length + second.length];
        int firstIndex = 0;
        int secondIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (firstIndex >= first.length) {
                array[i] = second[secondIndex];
                secondIndex++;
                continue;
            } else if (secondIndex >= second.length) {
                array[i] = first[firstIndex];
                firstIndex++;
                continue;
            }
            if (first[firstIndex] < second[secondIndex]) {
                array[i] = first[firstIndex];
                firstIndex++;
            } else {
                array[i] = second[secondIndex];
                secondIndex++;
            }
        }
        return array;
    }
    public int[] sortedArray(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int fl = first.length - 1;
        int sl = second.length - 1;
        int rl = result.length;

        while (rl > 0) {
            if (sl < 0 || fl > 0 && first[fl] >= second[sl]) {
                result[--rl] = first[fl--];
            } else {
                result[--rl] = second[sl--];
            }
        }
        return result;
    }
}
