package ru.job4j.letscode;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.02.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class AccumulTest {

    @Test
    public void name() {
        Accumul accumul = new Accumul();
        assertThat(accumul.accum("ZpglnRxqenU"),
                is("Z-Pp-Ggg-Llll-Nnnnn-Rrrrrr-Xxxxxxx-Qqqqqqqq-Eeeeeeeee-Nnnnnnnnnn-Uuuuuuuuuuu"));
    }
    @Test
    public void name2() {
        Accumul accumul = new Accumul();
        assertThat(accumul.accum("NyffsGeyylB"),
                is("N-Yy-Fff-Ffff-Sssss-Gggggg-Eeeeeee-Yyyyyyyy-Yyyyyyyyy-Llllllllll-Bbbbbbbbbbb"));
    }
    @Test
    public void name3() {
        Accumul accumul = new Accumul();
        assertThat(accumul.countBits(1234),
                is(5));
    }
}
