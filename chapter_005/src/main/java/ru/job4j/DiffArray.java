package ru.job4j;

import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.12.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class DiffArray {

    private static final Logger LOG = LogManager.getLogger(DiffArray.class);

    public Pair<ArrayList<Integer>, ArrayList<Integer>> diff(int[] src) {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        Pair<ArrayList<Integer>, ArrayList<Integer>> result = new Pair(a, b);
        if (src.length > 0) {
            Arrays.sort(src);
            int sum1 = 0;
            int sum2 = 0;
            for (int i = src.length - 1; i >= 0; i--) {
                if (sum1 < sum2) {
                    a.add(src[i]);
                    sum1 += src[i];
                } else {
                    b.add(src[i]);
                    sum2 += src[i];
                }
            }
        }
        return result;
    }
}
