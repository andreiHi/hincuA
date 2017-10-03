package ru.job4j.litle.treemapsort;

import org.junit.Test;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class TreeMapSortTest {
    /**
     *тест сортировки по возрастанию.
     */
    @Test
    public void whenTreeSetSortedByIncrease() {
    TreeMapSort treeMapSort = new TreeMapSort();
    TreeMap<int[], Object> map = treeMapSort.sortByIncrease(new int[]{1, 2, 3});
            treeMapSort.sortByIncrease(new int[]{1, 2, 0});
            treeMapSort.sortByIncrease(new int[]{1, 2, 0, 1});
        for (Map.Entry<int[], Object> m : map.entrySet()) {
            int[] k = m.getKey();
            System.out.println(Arrays.toString(k));
        }
    }

    /**
     * тест сортировки по убыванию.
     */
    @Test
    public void whenTreeSetSortedByWaning() {
        TreeMapSort treeMapSort = new TreeMapSort();
        TreeMap<int[], Object> map = treeMapSort.sortByWaning(new int[]{1, 2, 3});
        treeMapSort.sortByWaning(new int[]{1, 2, 0});
        treeMapSort.sortByWaning(new int[]{1, 2, 0, 1});
        for (Map.Entry<int[], Object> m : map.entrySet()) {
            int[] k = m.getKey();
            System.out.println(Arrays.toString(k));
        }

    }
}