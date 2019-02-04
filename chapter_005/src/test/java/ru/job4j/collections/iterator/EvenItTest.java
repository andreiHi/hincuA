package ru.job4j.collections.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 30.09.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class EvenItTest {
    /**
     * Дёргаем next в цикле.
     */
    @Test
    public void whenToArrayAddedThreeElements() {
        EvenIt evenIt = new EvenIt(new int[]{4, 2, 1, 1, 6});
        Iterator<Integer> it = evenIt.iterator();
        String result = "";
        while (it.hasNext()) {
            result += it.next();
        }
        String ex = "426";
        assertThat(result, is(ex));
    }

    /**
     *Дёргаем next в ручную.
     */
    @Test
    public void whenArrayHasNotElementsEven() {
        EvenIt evenIt = new EvenIt(new int[]{4, 2, 1, 1});
        Iterator<Integer> it = evenIt.iterator();
        it.next();
        it.next();
        boolean result = it.hasNext();
        assertThat(result, is(false));
    }
    @Test
    public void test() {
        EvenIt evenIt = new EvenIt(new int[]{4, 2, 1, 1});
        Iterator<Integer> it = evenIt.iterator();
        it.hasNext();
        it.hasNext();
        int i = (int) it.next();
        System.out.println(i);
    }

    @Test
    public void shoul() {
        EvenIt evenIt = new EvenIt(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        Iterator<Integer> it = evenIt.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
