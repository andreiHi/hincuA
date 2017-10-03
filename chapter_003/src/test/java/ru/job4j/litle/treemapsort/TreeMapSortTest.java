package ru.job4j.litle.treemapsort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class TreeMapSortTest {
    @Test
    public void name() throws Exception {
    TreeMapSort treeMapSort = new TreeMapSort();
    TreeMap<int[],Object>maps = new TreeMap<>();
    TreeMap<int[], Object> map = treeMapSort.sortByIncrease(new int[]{1,2,3});
            treeMapSort.sortByIncrease(new int[]{1,2,0});
            treeMapSort.sortByIncrease(new int[]{1,1});
            treeMapSort.sortByIncrease(new int[]{0});
            treeMapSort.sortByIncrease(new int[]{1,1,1});
            treeMapSort.sortByIncrease(new int[]{1,2,0, 1});
            treeMapSort.sortByIncrease(new int[]{2,2,0});
        for (Map.Entry<int[], Object> m : map.entrySet()) {
            int[] k = m.getKey();
            System.out.println(Arrays.toString(k));
        }

    }
}