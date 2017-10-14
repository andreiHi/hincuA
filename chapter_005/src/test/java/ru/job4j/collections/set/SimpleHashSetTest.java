package ru.job4j.collections.set;

import org.junit.Test;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 13.10.17;
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashSetTest {

    @Test
    public void name() throws Exception {
        SimpleHashSet<Integer> set = new SimpleHashSet();
        for(int i = 0; i < 10;i++) {
            set.put(i);
        }
    }
}