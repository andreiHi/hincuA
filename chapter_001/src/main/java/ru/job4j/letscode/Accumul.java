package ru.job4j.letscode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Accumul {
    private static final Logger LOG = LogManager.getLogger(Accumul.class);

    public String accum(String s) {
        final char[] chars = s.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char[] c = new char[i + 1];
            Arrays.fill(c, chars[i]);
            c[0] = Character.toUpperCase(c[i]);
            sb.append(new String(c)).append("-");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public int countBits(int n) {
        return (int) Integer.toBinaryString(n)
                .chars()
                .filter(x -> x == '1')
                .count();
    }
}
