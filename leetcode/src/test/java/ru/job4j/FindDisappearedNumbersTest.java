package ru.job4j;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FindDisappearedNumbersTest {

    @Test
    public void whenMiss5And6() {
        FindDisappearedNumbers disappearedNumbers = new FindDisappearedNumbers();
        List<Integer> num = disappearedNumbers.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        assertThat(Arrays.asList(5, 6), is(num));
    }

    @Test
    public void whenMiss2() {
        FindDisappearedNumbers disappearedNumbers = new FindDisappearedNumbers();
        List<Integer> num = disappearedNumbers.findDisappearedNumbers(new int[]{1, 1});
        assertThat(List.of(2), is(num));
    }

    @Test
    public void whenMiss5And6and() {
        FindDisappearedNumbers disappearedNumbers = new FindDisappearedNumbers();
        List<Integer> num = disappearedNumbers.findDisappearedNumbers2(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        assertThat(Arrays.asList(5, 6), is(num));
    }

    @Test
    public void whenMiss2and() {
        FindDisappearedNumbers disappearedNumbers = new FindDisappearedNumbers();
        List<Integer> num = disappearedNumbers.findDisappearedNumbers2(new int[]{1, 1});
        assertThat(List.of(2), is(num));
    }
}