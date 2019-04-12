package ru.job4j.letscode;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The new "Avengers" movie has just been released! There are a lot of people at the cinema box office standing
 * in a huge line. Each of them has a single 100, 50 or 25 dollars bill. An "Avengers" ticket costs 25 dollars.
 * Vasya is currently working as a clerk. He wants to sell a ticket to every single person in this line.
 * Can Vasya sell a ticket to each person and give the change if he initially has no money and sells the tickets
 * strictly in the order people follow in the line?
 * Return YES, if Vasya can sell a ticket to each person and give the change with the bills he has at hand at that moment.
 * Otherwise return NO.
 * Line.tickets(new int[] {25, 25, 50}) // => YES
 * Line.tickets(new int[]{25, 100}) // => NO. Vasya will not have enough money to give change to 100 dollars
 * Line.tickets(new int[] {25, 25, 50, 50, 100}) // => NO. Vasya will not have the right bills to give 75 dollars of change
 * (you can't make two bills of 25 from one of 50)
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.04.2019.
 * @version $Id$.
 * @since 0.1.
 */

public class Line {

    public static String tickets(int[] peopleInLine) {
        String result = "YES";
        String no = "NO";
        if (peopleInLine[0] == 25) {
            Map<Integer, Integer> bank = new HashMap<>();
            bank.put(25, 1);
            bank.put(50, 0);
            bank.put(100, 0);
            for (int i = 1; i < peopleInLine.length; i++) {
                if (peopleInLine[i] == 25) {
                    bank.put(25, bank.get(25) + 1);
                }
                if (peopleInLine[i] == 100) {
                    if (bank.get(50) == 0 && bank.get(25) < 3 || bank.get(50) > 0 && bank.get(25) < 1) {
                        result = no;
                        break;
                    } else if (bank.get(50) > 0) {
                        bank.put(50, bank.get(50) - 1);
                        bank.put(25, bank.get(25) - 1);
                    } else {
                        bank.put(25, bank.get(25) - 3);
                    }
                    bank.put(100, bank.get(100) + 1);
                }
                if (peopleInLine[i] == 50) {
                    if (bank.get(25) == 0) {
                        result = no;
                        break;
                    } else {
                        bank.put(25, bank.get(25) - 1);
                        bank.put(50, bank.get(50) + 1);
                    }
                }
            }
        } else {
            result = no;
        }
        return result;
    }

    public static void main(String[] args) {
        final Comparator<String> comparator = (o1, o2) -> 0;
    }

}
























