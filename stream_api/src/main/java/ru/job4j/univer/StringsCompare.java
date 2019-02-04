package ru.job4j.univer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.IntStream;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class StringsCompare {
    private static final Logger LOG = LogManager.getLogger(StringsCompare.class);

    public final int compare(final String left, final String right) {
        int min = Math.min(left.length(), right.length());
        return IntStream.range(0, min)
                .map(z -> Character.compare(left.charAt(z), right.charAt(z)))
                .filter(z -> z != 0)
                .findFirst()
                .orElse(Integer.compare(left.length(), right.length()));
    }
}
