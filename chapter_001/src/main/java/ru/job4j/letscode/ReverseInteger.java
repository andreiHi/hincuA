package ru.job4j.letscode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class ReverseInteger {


    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        } else {
            return (int) res;
        }
    }
}
