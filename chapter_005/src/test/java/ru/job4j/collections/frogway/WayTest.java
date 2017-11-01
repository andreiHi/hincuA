package ru.job4j.collections.frogway;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class WayTest {
    @Test
    public void cenMove() {
        Position position = new Position(7, 11);
        boolean b = position.canMoveToPosition(-6, 1);
        assertThat(b, is(true));
    }

    @Test
    public void name() throws Exception {
    Way way = new Way();
    way.srart();
    }
}