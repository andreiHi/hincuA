package ru.job4j.letscode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 29.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class SquareDigitTest {
    @Test
    public void test() {
        assertEquals(811181, new SquareDigit().squareDigits(9119));
    }
}
