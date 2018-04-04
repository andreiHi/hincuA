package ru.job4j;

import com.google.gson.Gson;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.04.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class TicTacTest {

    @Test
    public void test() {
        TicTac ticTac = new TicTac(new int[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
        boolean result = ticTac.hasWinner();
        assertThat(result, is(false));
        System.out.println(new Gson().toJson("ddd", String.class));
    }

}