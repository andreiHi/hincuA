package ru.job4j.litle;

import java.util.Comparator;

/**
 * Класс ListCompare
 * @author - Dorokhov Sergey
 * @since - 06.08.2018
 * @version - 01
 */
public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = left.length() - right.length();
        int minL = left.length() < right.length() ? left.length() : right.length();
        for (int i = 0; i < minL; i++) {
           int res = Character.compare(left.charAt(i), right.charAt(i));
            if (res != 0) {
                result = res;
                break;
            }
        }
        return result;
    }

}