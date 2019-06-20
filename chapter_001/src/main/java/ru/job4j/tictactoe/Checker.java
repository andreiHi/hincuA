package ru.job4j.tictactoe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Checker {
//    check([1, 2, 3, 6], 9) // true: 6+3=9
//    check([1, 1, 3, 4], 2) // true: 1+1=2
//    check([-1, 1, 4, 8], 3) // true: -1+4=3
//    check([1, 4, 8, 9], 6)

    public boolean check(int[] array, int a) {
        boolean res = false;
        Set<Integer> nums = new HashSet<>();
        for (int i : array) {
            int t = a - i;
            if (nums.contains(i)) {
                res = true;
                break;
            }
           nums.add(t);
        }
        return res;
    }
}
