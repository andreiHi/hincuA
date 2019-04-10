package ru.job4j.loop;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 06.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class SummTest {

    @Test
    public void name() {
        Summ summ = new Summ();
        final int sum = summ.sum(new int[]{1, 2, 3, 4, 5});
        System.out.println(sum);
    }
}
