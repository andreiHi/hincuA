package ru.job4j;

import java.util.Arrays;


public class CyclicRotation {
//{3, 8, 9, 7, 6}
    //new int[]{9, 7, 6, 3, 8}
    public int[] solution(int[] a, int k) {
        if (k == a.length || a.length == 0) {
            return a;
        }
        int[] ints = Arrays.copyOf(a, a.length - k);
        System.arraycopy(a, a.length - k, a, 0, a.length - (a.length  - k));
        System.arraycopy(ints, 0, a, k, ints.length);
        return a;
    }

}
