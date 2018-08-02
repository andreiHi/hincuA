package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * CounterTest.
 * @author Hincu Andrei (andreih1981@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CounterTest {
    /**
     * Test counter.
     */
    @Test
    public void whenStartOneAndFinishTenThenThirty() {
        Counter counter = new Counter();
        int result = counter.add(9, 10);
        int ex = 10;
        assertThat(result, is(ex));
    }
}
