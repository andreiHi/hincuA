package ru.job4j.litle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ConvertList.
 * @author Hincu Andrei (andreih1981@gmail.com) by 16.09.17;
 * @version $Id$
 * @since 0.1
 */
public class ConvertList {
    /**
     * конвертирует простой массив в ArrayList.
     *
     * @param array двумерный массив.
     * @return ArrayList.
     */
    public List<Integer> toList2(int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }

    /**
     * Конвертирует ArrayList в двумерный массив.
     *
     * @param list арайлист.
     * @param rows колличествострок.
     * @return двумерный массив.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int colum = 0;
        if (list.size() % rows == 0) {
            colum = list.size() / rows;
        } else {
            colum = list.size() / rows + 1;
        }
        int count = 0;
        int[][] array = new int[rows][colum];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colum; j++) {
                if (count < list.size()) {
                    array[i][j] = list.get(count);
                } else {
                   break;
                }
                count++;
            }
        }
        return array;
    }

    /**
     * Конвертация листа массивов в один лист Integer.
     *
     * @param list лист массивов.
     * @return лист Integer.
     */
    public List<Integer> convert(List<int[]> list) {
       return list.stream()
               .flatMapToInt(Arrays::stream)
               .boxed()
               .collect(Collectors.toCollection(ArrayList::new));

    }

    /**
     * Конвертация листа с null в массив.
     * @param list list.
     * @return массив.
     */
    public int[] convert1(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                array[i] = 0;
            } else {
                array[i] = list.get(i);
            }
        }
        return array;
    }

    public List<Integer> toList(int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] a : array) {
            for (int b : a) {
                list.add(b);
            }
        }
        return list;
    }
    public List<Integer> toList1(int[][] array) {
        return Arrays.stream(array).flatMapToInt(IntStream::of).boxed()
                .collect(Collectors.toList());
    }

    public int[][] toArray2(List<Integer> list, int rows) {
        int cols = list.size() % rows == 0
                ? list.size() / rows
                : list.size() / rows + 1;
        int[][] result = new int[rows][cols]; //filled with "0" by default
        int[] iRows = {0};
        int[] iCols = {0};
        list.forEach(element -> {
                    result[iRows[0]][iCols[0]++] = element;
                    if (iCols[0] >= cols) {
                        iRows[0]++;
                        iCols[0] = 0;
                    }
                }
        );

        System.out.println(rows + " " + cols);

        final int[] count = {0};
        Integer[][] array =
                IntStream.range(0, rows)
                        .mapToObj(x -> IntStream.range(0, cols)
                                .mapToObj(y -> count[0] >= list.size() ? 0 : list.get(count[0]++))
                                .toArray(Integer[]::new))
                        .toArray(Integer[][]::new);


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        return result;
    }

    public static int letter(int i) {
        return Character.forDigit(i, Character.MAX_RADIX);
    }
}
