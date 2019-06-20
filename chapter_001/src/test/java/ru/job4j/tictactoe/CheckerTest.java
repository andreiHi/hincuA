package ru.job4j.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 19.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class CheckerTest {

    @Test
    public void name() {
        Checker checker = new Checker();
        boolean check = checker.check(new int[]{1, 2, 3, 6}, 9);
        assertTrue(check);
    }
    @Test
    public void name2() {
        Checker checker = new Checker();
        boolean check = checker.check(new int[]{1, 1, 3, 4}, 2);
        assertTrue(check);
    }
    @Test
    public void name3() {
        Checker checker = new Checker();
        boolean check = checker.check(new int[]{-1, 1, 4, 8}, 3);
        assertTrue(check);
    }
    @Test
    public void name4() {
        Checker checker = new Checker();
        boolean check = checker.check(new int[]{1, 4, 8, 9}, 6);
        assertFalse(check);
    }


}
