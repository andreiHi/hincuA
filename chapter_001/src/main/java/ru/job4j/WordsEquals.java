package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class WordsEquals {
    private static final Logger LOG = LogManager.getLogger(WordsEquals.class);

    private Map<Character, Integer> chars = new HashMap<>();

    public boolean equalsWords(String f, String s) {
        char[]chars = f.toCharArray();
        for (char aChar : chars) {
            final Integer integer = this.chars.putIfAbsent(aChar, 1);
            if (integer != null) {
                this.chars.computeIfPresent(aChar, (k, v) -> v += 1);
            }
        }
        final char[] sChars = s.toCharArray();
        for (char sChar : sChars) {
            final Integer integer = this.chars.get(sChar);
            if (integer == null) {
                break;
            }
            if (integer == 1) {
                this.chars.remove(sChar);
            } else {
               this.chars.computeIfPresent(sChar, (k, v) -> v -= 1);
            }
        }
        LOG.trace("trace message");
        return this.chars.isEmpty();
    }
}
