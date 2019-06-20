package ru.job4j.scool;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int f = 9 - i;
        System.out.println(Integer.valueOf(i + "" + 9 + "" + f));
    }
}
