package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 17.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class CyclicRotationTest {

    @Test
    public void name() {
        CyclicRotation c = new CyclicRotation();
        int[] ints = c.solution(new int[]{3, 8, 9, 7, 6}, 3);
        assertThat(ints, is(new int[]{9, 7, 6, 3, 8}));
    }
    @Test
    public void name2() {
        CyclicRotation c = new CyclicRotation();
        int[] ints = c.solution(new int[]{0, -1000}, 1);
        assertThat(ints, is(new int[]{-1000, 0}));
    }
}
