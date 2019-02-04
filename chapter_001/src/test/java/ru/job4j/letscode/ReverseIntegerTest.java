package ru.job4j.letscode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class ReverseIntegerTest {

    @Test
    public void name() {
        ReverseInteger ri = new ReverseInteger();
        final int reverse = ri.reverse(321);
        assertEquals(reverse, 123);
    }
    @Test
    public void name2() {
        ReverseInteger ri = new ReverseInteger();
        final int reverse = ri.reverse(120);
        assertEquals(reverse, 21);
    }
    @Test
    public void name3() {
        ReverseInteger ri = new ReverseInteger();
        final int reverse = ri.reverse(-321);
        assertEquals(reverse, -123);
    }
    @Test
    public void name4() {
        ReverseInteger ri = new ReverseInteger();
        final int reverse = ri.reverse(1534236469);
        assertEquals(reverse, 0);
    }
}
