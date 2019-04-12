package ru.job4j.letscode;

import java.util.Scanner;

public class GoodString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = Integer.valueOf(sc.nextLine());
        String s = sc.nextLine();
        char[] chars = s.toCharArray();
        int i = 0;
        int j = 1;
        StringBuilder sb = new StringBuilder();
        while (j < chars.length) {
            if (chars[i] != chars[j]) {
                sb.append(chars[i]).append(chars[j]);
                j = j + 2;
                i = j - 1;
            } else {
                j++;
            }
        }
        if (sb.length() % 2 != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(len - sb.length());
        System.out.println(sb.toString());
    }
}
