package ru.job4j;


import java.util.Scanner;

public class Lose {
    static int[] ints = new int[]{4, 8, 15, 16, 42, 23};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int res = 0;
        if (l < 6) {
            System.out.println(l);
        } else {
            int c = l / 6;
            for (int i = 0; i < c; i++) {
                System.out.println(i);
            }
        }

    }


}
