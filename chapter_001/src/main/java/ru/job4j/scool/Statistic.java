package ru.job4j.scool;

import java.util.Scanner;
import java.util.StringJoiner;

public class Statistic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = Integer.valueOf(sc.nextLine());
        String st = sc.nextLine();
        String[]strings = st.split(" ");
        int[] arr = new int[i];
        for (int j = 0; j < i; j++) {
            arr[j] = Integer.valueOf(strings[j]);
        }
        StringJoiner odd = new StringJoiner(" ");
        StringJoiner edd = new StringJoiner(" ");
        int count = 0;
        int count2 = 0;
        for (int in : arr) {
            if (in % 2 == 0) {
                edd.add(String.valueOf(in));
                count++;
            } else {
                odd.add(String.valueOf(in));
                count2++;
            }
        }
        System.out.println(odd);
        System.out.println(edd);
        if (count2 <= count) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
