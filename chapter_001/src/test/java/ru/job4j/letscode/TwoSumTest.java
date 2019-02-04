package ru.job4j.letscode;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 21.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class TwoSumTest {
    @Test
    public void name() {
        TwoSum twoSum = new TwoSum();
        final int[] ints = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
        assertThat(ints[0], is(0));
        assertThat(ints[1], is(1));
    }

    @Test
    public void name2() {
        TwoSum twoSum = new TwoSum();
        final int[] ints = twoSum.twoSum(new int[]{3, 2, 4}, 6);
        assertThat(ints[0], is(1));
        assertThat(ints[1], is(2));
    }
}
