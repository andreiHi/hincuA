package ru.job4j;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.12.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class DiffArrayTest {
    @Test
    public void test1() {
        DiffArray difArr = new DiffArray();
        Pair<ArrayList<Integer>, ArrayList<Integer>> p = difArr.diff(new int[] {1, 2, 5, 10});
        assertThat(p.getKey().toArray(), is(new Integer[] {5, 2, 1}));
        assertThat(p.getValue().toArray(), is(new Integer[] {10}));
    }

    @Test
    public void test2() {
        DiffArray difArr = new DiffArray();
        Pair<ArrayList<Integer>, ArrayList<Integer>> p = difArr.diff(new int[] {3, 4, 8, 10});
        assertThat(p.getKey().toArray(), is(new Integer[] {8, 4}));
        assertThat(p.getValue().toArray(), is(new Integer[] {10, 3}));
    }

    @Test
    public void test3() {
        DiffArray difArr = new DiffArray();
        Pair<ArrayList<Integer>, ArrayList<Integer>> p = difArr.diff(new int[] {3, 4, 8, 3});
        assertThat(p.getKey().toArray(), is(new Integer[]{4, 3, 3}));
        assertThat(p.getValue().toArray(), is(new Integer[]{8}));
    }
}