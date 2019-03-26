package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 12.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ServiceTest {
    private String file = "number.txt";
    private String input = "input.txt";
    private InputStream is;
    private Service service;
    String[]ab;
    private InputStream is2;

    @Before
    public void init() throws FileNotFoundException {
        service = new Service();
        is = getClass().getClassLoader().getResourceAsStream(file);
        ab = new String[]{"уппс", "чух", "пух"};
        is2 = getClass().getClassLoader().getResourceAsStream(input);
    }

    @Test
    public void isEvenNumber() {
        boolean result =  service.isNumber(is);
        assertThat(result, is(true));
    }

    @Test
    public void whenTextContainsAnyWordsThenNoWords() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        service.dropAbuses(is2, bos, ab);
        String s = bos.toString();
        boolean contains = false;
        for (String abuse : ab) {
            if (s.contains(abuse)) {
                contains = true;
                break;
            }
        }
        assertFalse(contains);
    }
    @Test
    public void whenTextContainsAnyWordsThenNoWords2() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        service.dropAbuses2(is2, bos, ab);
        String s = bos.toString();
        System.out.println(s);
        boolean contains = false;
        for (String abuse : ab) {
            if (s.contains(abuse)) {
                contains = true;
                break;
            }
        }
        assertFalse(contains);

    }

    @Test
    public void name() {
        Stream<String> wordsStream = Stream.of("мама", "мыла", "раму");
        String sentence = wordsStream.reduce("Результат:", (x, y)-> x + " " + y);
        System.out.println(sentence); // Результат: мама мыла раму
    }

    @Test
    public void name2() {
        Stream<String> wordsStream = Stream.of("мама", "мыла", "раму");
        String[] ab = {"мыла"};
         wordsStream.map(s -> {
             String finalS = s;
             s = Arrays.stream(ab)
                     .reduce(s, new BinaryOperator<String>() {
                         @Override
                         public String apply(String a, String b) {
                             System.out.println("s + a + b " + " " + finalS + " " + a + " " + b);
                             return a.replaceAll(b, "");
                         }
                     });
             return s;
        }).forEach(a -> System.out.println("out :" + a));
    }

    @Test
    public void name3() {

        final boolean mkdirs = new File(System.getProperty("java.io.tmpdir") + "/TEST_DIR").mkdirs();
        System.out.println(mkdirs + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
