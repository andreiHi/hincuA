package ru.job4j;

import java.util.HashMap;
import java.util.Map;

public class OddOccurrencesInArray {


    public int solution(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int b = a[i];
            if (map.remove(b) == null) {
                map.put(b, i);
            }
        }
        return map.keySet().iterator().next();
    }
}
