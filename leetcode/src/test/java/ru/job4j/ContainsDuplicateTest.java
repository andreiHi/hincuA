package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContainsDuplicateTest {

    @Test
    public void whenContains() {
        ContainsDuplicate duplicate = new ContainsDuplicate();
        boolean contains = duplicate.containsDuplicate(new int[]{1, 2, 3, 1});
        assertTrue(contains);
    }
    @Test
    public void whenNotContains() {
        ContainsDuplicate duplicate = new ContainsDuplicate();
        boolean contains = duplicate.containsDuplicate(new int[]{1,2,3,4});
        assertFalse(contains);
    }

    @Test
    public void whenNotALotContains() {
        ContainsDuplicate duplicate = new ContainsDuplicate();
        boolean contains = duplicate.containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2});
        assertTrue(contains);
    }
}
