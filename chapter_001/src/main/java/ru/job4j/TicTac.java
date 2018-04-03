package ru.job4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class TicTac {
    private static final Logger LOG = LogManager.getLogger(TicTac.class);
    private int[][] values;

    public TicTac(int[][] values) {
        this.values = values;
    }
    public boolean hasWinner() {
        boolean found = false;

        return found;
    }
}
