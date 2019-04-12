package ru.job4j.letscode;

import java.util.Scanner;
public class Rest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nL = scanner.nextLine();
        String s = scanner.nextLine();
        final char[] chars = s.toCharArray();
        int count = 0;
        String[]l = nL.split(" ");
        int x = Integer.valueOf(l[1]);
        int y = Integer.valueOf(l[2]);

        if (chars[chars.length - 1 - y] == '1') {
            count--;
        } else {
            count++;
        }
        for (int i = chars.length - 1; x > 0; i--) {
            x--;
            if (chars[i] != '0') {
                count++;
            }
        }
        System.out.println(count);
    }
}

