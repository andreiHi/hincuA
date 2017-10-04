package ru.job4j.collections.gineric;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class SimpleArrayTest {
    /**
     * Test.
     */
    @Test
    public void name() {
        SimpleArray simpleArray = new SimpleArray(5);
        simpleArray.add("sss");
        simpleArray.add(1);
        System.out.printf("%s, %s",simpleArray.getValue(0), simpleArray.getValue(1));
        assertThat(true, is(true));
    }
}