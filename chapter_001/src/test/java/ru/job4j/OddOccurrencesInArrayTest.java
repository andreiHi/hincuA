package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 17.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class OddOccurrencesInArrayTest {

    @Test
    public void name() {
        OddOccurrencesInArray array = new OddOccurrencesInArray();
        int i = array.solution(new int[]{9, 3, 9, 3, 9, 7, 9});
        assertThat(i, is(7));
    }
}
