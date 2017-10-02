package treemapsort;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class TreeMapSort {
    TreeMap<int[],Object> map;

    public TreeMapSort(TreeMap<int[], Object> map) {
        this.map = map;
        map.put(new int[]{1, 2, 3},null);
        map.put(new int[]{1, 2, 0},null);
        map.put(new int[]{1, 2, 3, 1},null);
    }

    public TreeMapSort(IntByIncrease intByIncrease) {

    }
}