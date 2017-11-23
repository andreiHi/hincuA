package ru.job4j.multithreading.bomber;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class BomberTest {
    Bomber bomber;
    Start start;
    @Before
    public void start() {
        start = new Start();
        bomber = new Bomber(start);
    }


    @Test
    public void move() throws Exception {
    }


    /**
     * Тест границ поля.
     */
    @Test
    public void checkBorders() {
       boolean result = bomber.checkBorders(21, 5);
       assertFalse(result);
       result = bomber.checkBorders(5, 5);
       assertTrue(result);
    }

}