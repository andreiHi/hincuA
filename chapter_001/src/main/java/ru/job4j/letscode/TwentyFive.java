package ru.job4j.letscode;

import java.util.Scanner;

public class TwentyFive {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long i = sc.nextInt();
        long res;
        if (i < 10) {
            res = i * i;
        } else {
            i = i / 10;
            i = i * (i + 1);
            res = Long.valueOf(i + "25");
        }
        System.out.println(res);
    }
}
