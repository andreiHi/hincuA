package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MissingNumberTest {

    @Test
    public void whenMiss2() {
        MissingNumber number = new MissingNumber();
        int num = number.missingNumber(new int[]{3, 0, 1});
        assertThat(2, is(num));
    }

    @Test
    public void whenMiss2Again() {
        MissingNumber number = new MissingNumber();
        int num = number.missingNumber(new int[]{0, 1});
        assertThat(2, is(num));
    }

    @Test
    public void whenMiss8() {
        MissingNumber number = new MissingNumber();
        int num = number.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1});
        assertThat(8, is(num));
    }

    @Test
    public void whenMiss2Second() {
        MissingNumber number = new MissingNumber();
        int num = number.missingNumber2(new int[]{3, 0, 1});
        assertThat(2, is(num));
    }

    @Test
    public void whenMiss2AgainSecond() {
        MissingNumber number = new MissingNumber();
        int num = number.missingNumber2(new int[]{0, 1});
        assertThat(2, is(num));
    }

    @Test
    public void whenMiss8Second() {
        MissingNumber number = new MissingNumber();
        int num = number.missingNumber2(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1});
        assertThat(8, is(num));
    }
}