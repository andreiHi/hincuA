package ru.job4j.collections.set;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 13.10.17;
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashSetTest {
    @Test
    public void put() throws Exception {
        String a = "5";
        int av = hash(a.hashCode());
        int index = indexFor(av, 16);
        System.out.println(index);
    }
    static int hash(int h)
    {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    static int indexFor(int h, int length)
    {
        return h & (length - 1);
    }
}