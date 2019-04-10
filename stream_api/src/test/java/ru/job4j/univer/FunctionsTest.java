package ru.job4j.univer;

import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.function.IntBinaryOperator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class FunctionsTest {

    @Test
    public void testDiapason() {
        Functions functions = new Functions();
        functions.diapason(1, 5, x -> Math.pow(x, 2)).forEach(System.out::println);
    }

    @Test
    public void partialSumTest() {
        Functions func = new Functions();
        final IntBinaryOperator intBin = func.partialSum(2);
        assertThat(intBin.applyAsInt(5, 3), is(10));
    }

    @Test
    public void carrySumTest() {
        final Functions func = new Functions();
        assertThat(func.currySum().apply(5).apply(3).applyAsInt(2), is(10));
    }

    @Test
    public void name() {
        final URL resource = getClass().getClassLoader().getResource("log4j.xml");
        final File file = new File(resource.getFile());
        final String parent = file.getParent();
        System.out.println(parent);
        System.out.println(file.exists());
        System.out.println(resource.toString());
    }
}

