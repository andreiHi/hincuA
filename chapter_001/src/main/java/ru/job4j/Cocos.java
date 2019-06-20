package ru.job4j;

//582426543750017 789023129080207 4395672028302
//5 4 3

//long sasha = 5;
//        long masha = 4;
//        long cocos = 3;

import java.util.Scanner;

public class Cocos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long sasha = sc.nextLong();
        long masha = sc.nextLong();
        long cocos = sc.nextLong();

        long  saCoc = sasha / cocos;
        long masCoc = masha / cocos;
        long temp = saCoc + masCoc;

        sasha = sasha - saCoc * cocos;
        masha = masha - masCoc * cocos;

        long add = 0;
        if (sasha + masha >= cocos) {
            long b = sasha > masha ? sasha : masha;
             add = cocos - b;
            temp++;
        }
        System.out.println(temp + " " + add);
    }
}
