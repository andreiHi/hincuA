package ru.job4j.litle.treemapsort;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class TreeMapSort {
    TreeMap<int[], Object>map = new TreeMap<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] ints, int[] t1) {
            int temp = ints.length - t1.length;
            if (temp == 0) {
                    for (int i = 0; i < ints.length; ) {
                        temp = ints[i] - t1[i];
                        if (temp == 0) {
                            i++;
                        } else {
                            break;
                        }
                    }
            } else if (temp > 0) {
                for (int i = 0; i < t1.length;i++) {
                    temp = ints[i] - t1[i];
                }
                if (temp == 0) {
                    temp = ints.length - t1.length;
                }
            }
            return temp;
        }
    });
    public TreeMap<int[], Object>sortByIncrease(int[] m ) {
        map.put(m, null);
        return map;
    }
}