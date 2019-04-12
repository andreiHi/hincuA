package ru.job4j.letscode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 29.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Persist {
    public static int persistence(long n) {
        String num = String.valueOf(n);
        if (num.contains("0")) {
            return 1;
        }
        int count = 0;
        int res;
        do {
            res = get(num);
            num = String.valueOf(res);
            count++;
        } while (String.valueOf(res).length() != 1);
        return count == 1 ? 0 : count;
    }

    private static int get(String num) {
        int res = 1;
        for (char c : num.toCharArray()) {
            res *= Character.digit(c, 10);
        }
        return res;
    }
}
