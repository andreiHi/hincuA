package ru.job4j.array;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class InsertionSortTest {

    @Test
    public void name() {
        InsertionSort sort = new InsertionSort();
        final int[] ints = sort.insertionSort(new int[]{5, 2, 4, 6, 1, 3});
        System.out.println(Arrays.toString(ints));
        int[]ex = {1, 2, 3, 4, 5, 6};
        assertArrayEquals(ex, ints);
    }
}
