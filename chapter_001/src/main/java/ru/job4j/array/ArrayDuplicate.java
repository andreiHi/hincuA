package ru.job4j.array;

import java.util.Arrays;


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


    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (start % 2 == 0) {
                sum = sum + i;
            }
        }
        return sum;
    }
    public int[]add(int[]a, int[]b) {
        int[]result = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int index = 0;
        do {
            if (i < a.length && j < b.length) {
                if (a[i] < b[j]) {
                    result[index++] = a[i];
                    i++;
                } else {
                    result[index++] = b[j];
                    j++;
                }
            } else if (i == a.length && j < b.length) {
                result[index++] = b[j];
                j++;
            } else if (j == b.length || i < a.length) {
                result[index++] = a[i];
                i++;
            }
        } while (index < result.length);
        return result;
    }
    public String[] remove2(String[] array) {
        int copies = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int a = i + 1; a < array.length - copies; a++) {
                if (array[i].equals(array[a])) {
                    String replace = array[a];
                    for (int j = a; j < array.length - 1; j++) {
                        array[j] = array[j + 1];
                    }
                    array[array.length - 1] = replace;
                    copies++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - copies);
    }
    public String[] remove0(String[] data) {
        int index = 0;
        for (int i = 0; i < data.length - 1; i++) {
            int last = data.length - 1 - i;
            for (int j = i; j < last; j++) {
                if (data[i].equals(data[j + 1])) {
                    String tmp = data[last];
                    data[last] = data[j + 1];
                    data[j + 1] = tmp;
                    index = last; // запоминаем индекс с которого начинаются дубликаты
                }
            }
        }
        return Arrays.copyOf(data, index);
    }

}
