package ru.job4j.jdbc;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class OptimizatorTest {
   private Optimizator optimizator;
   ConnectionSqLite connectionSqLite;
   @Before
    public void init() {
       optimizator = new Optimizator();
       connectionSqLite = new ConnectionSqLite();
   }
    @Test
    public void create() {
       optimizator.createTestTable();
    }
}