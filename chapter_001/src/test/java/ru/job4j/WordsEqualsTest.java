package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 02.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class WordsEqualsTest {

    @Test
    public void name() {
        WordsEquals we = new WordsEquals();
        final boolean b = we.equalsWords("aabb", "bb");
        assertFalse(b);
    }
    @Test
    public void test() {
        WordsEquals we = new WordsEquals();
        final boolean b = we.equalsWords("aabb", "aabb");
        assertTrue(b);
    }
}
