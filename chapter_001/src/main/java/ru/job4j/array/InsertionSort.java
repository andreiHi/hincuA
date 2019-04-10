package ru.job4j.array;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class InsertionSort {

    public int[] insertionSort(int[]array) {
        for (int j = 1; j < array.length; j++) {
            int key = array[j];
            int i = j - 1;
            while (i > -1 && array[i] > key) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println(false ^ false);
    }
}
