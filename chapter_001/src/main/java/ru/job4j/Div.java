package ru.job4j;

import java.util.Scanner;

public class Div {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        long[] ar = new long[q];
        for (int i = 0; i < q; i++) {
           ar[i] = sc.nextLong();
        }
        prepear(ar);

    }
    public static void prepear(long[] i) {
        for (int j = 0; j < i.length; j++) {
            long in = i[j];
            boolean ex = true;
            int count = 0;
            int mod = count;
            while (ex) {
                if (in % 5 == 0) {
                    in = (4 * in) / 5;
                    mod++;
                }
                if (in % 3 == 0) {
                    in = (2 * in) / 3;
                    mod++;
                }
                if (in % 2 == 0) {
                    in = in / 2;
                    mod++;
                }
                if (mod == count) {
                    ex = false;
                    if (in != 1) {
                        System.out.println(-1);
                    } else {
                        System.out.println(count);
                    }
                } else {
                    count = mod;
                }
            }
        }
    }
}
