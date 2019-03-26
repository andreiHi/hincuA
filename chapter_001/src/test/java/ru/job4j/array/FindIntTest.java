package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 27.03.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class FindIntTest {

    @Test
    public void name() {
        FindInt findInt = new FindInt();
        final int i = findInt.find(new int[]{2, 7, 5, 4, 6, 1, 0});
        assertThat(i, is(3));
    }
}
