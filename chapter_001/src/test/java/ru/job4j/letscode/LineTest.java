package ru.job4j.letscode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class LineTest {
    @Test
    public void test1() {
        assertEquals("YES", Line.tickets(new int[] {25, 25, 50}));
    }
    @Test
    public void test2() {
        assertEquals("NO", Line.tickets(new int[]{25, 100}));
    }
    @Test
    public void test5() {
        assertEquals("YES", Line.tickets(new int[]{25, 25, 25, 100}));
    }
    @Test
    public void test3() {
        assertEquals("YES", Line.tickets(new int[]{25, 25, 25, 50, 100}));
    }
    @Test
    public void test4() {
        assertEquals("NO", Line.tickets(new int[]{25, 25, 50, 50, 100}));
        assertEquals("YES", Line.tickets(new int[]{25, 25, 50, 50, 25, 100}));
    }
    @Test
    public void test6() {
        assertEquals("NO", Line.tickets(new int[]{25, 25, 50, 50, 100}));
        assertEquals("YES", Line.tickets(new int[]{25, 25, 25, 100, 25, 50, 25, 100, 25, 50, 25, 100, 25, 50, 25, 100}));
    }


    @Test
    public void test1a() {
        assertEquals("YES", Line.tickets(new int[]{25, 25, 50}));
    }
    @Test
    public void test2s() {
        assertEquals("NO", Line.tickets(new int[]{25, 100}));
    }
    @Test
    public void test3f() {
        assertEquals("YES", Line.tickets(new int[]{25, 25, 25, 25, 25, 25, 25, 25, 25, 25}));
    }
    @Test
    public void test4g() {
        assertEquals("NO", Line.tickets(new int[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50}));
    }
    @Test
    public void test5r() {
        assertEquals("NO", Line.tickets(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100}));
    }
    @Test
    public void test6d() {
        assertEquals("YES", Line.tickets(new int[]{25, 25, 25, 25, 50, 100, 50}));
    }
    @Test
    public void test7() {
        assertEquals("NO", Line.tickets(new int[]{50, 100, 100}));
    }
    @Test
    public void test8() {
        assertEquals("NO", Line.tickets(new int[]{25, 25, 100}));
    }
    @Test
    public void test9() {
        assertEquals("NO", Line.tickets(new int[]{25, 25, 25, 25, 25, 25, 25, 50, 50, 50, 100, 100, 100, 100}));
    }
    @Test
    public void test10() {
        assertEquals("NO", Line.tickets(new int[]{25, 25, 50, 50, 100}));
    }
    @Test
    public void test11() {
        assertEquals("NO", Line.tickets(new int[]{25, 25, 25, 25, 25, 100, 100}));
    }
    @Test
    public void test12() {
        assertEquals("NO", Line.tickets(new int[]{100, 50, 25, 25}));
    }

}
