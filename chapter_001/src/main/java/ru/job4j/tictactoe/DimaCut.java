package ru.job4j.tictactoe;

import java.util.Scanner;

public class DimaCut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.valueOf(sc.nextLine());
        String i = sc.nextLine();
        int c = 1;
        long result = Long.MAX_VALUE;
        for (int a = 1; a < n; a++) {
            String fString = i.substring(0, a);
            String substring = i.substring(c);
            if (!substring.startsWith("0")) {
                long first = Long.valueOf(fString);
                long second = Long.parseLong(substring);
                if (result > (first + second)) {
                    result = (first + second);
                }
            }
            c++;
        }
        System.out.println(result);
    }
}
