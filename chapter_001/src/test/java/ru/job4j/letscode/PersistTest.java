package ru.job4j.letscode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 29.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class PersistTest {
    @Test
    public void basicTests() {
        System.out.println("****** Basic Tests ******");
        assertEquals(3, Persist.persistence(39));
        assertEquals(0, Persist.persistence(4));
        assertEquals(2, Persist.persistence(25));
        assertEquals(4, Persist.persistence(999));
       // assertEquals(0, Persist.persistence(454480));

    }
}
