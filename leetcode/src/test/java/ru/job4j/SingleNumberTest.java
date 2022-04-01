package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingleNumberTest {

    @Test
    public void whenHas2() {
        SingleNumber singleNumber = new SingleNumber();
        int i = singleNumber.singleNumber(new int[]{2, 2, 1});
        assertEquals(1, i);
    }

    @Test
    public void whenHas4() {
        SingleNumber singleNumber = new SingleNumber();
        int i = singleNumber.singleNumber(new int[]{4, 1, 2, 1, 2});
        assertEquals(4, i);
    }

    @Test
    public void whenHas1() {
        SingleNumber singleNumber = new SingleNumber();
        int i = singleNumber.singleNumber(new int[]{1});
        assertEquals(1, i);
    }
}