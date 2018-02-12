package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 12.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ServiceTest {
    private String file = "number.txt";
    private String input = "input.txt";
    private String output = "output.txt";
    private InputStream is;
    private  OutputStream ou;
    private Service service;
    String[]ab;

    @Before
    public void init() throws FileNotFoundException {
        service = new Service();
        is = getClass().getClassLoader().getResourceAsStream(file);
        ab = new String[]{"уппс", "чух", "пух"};
    }

    @Test
    public void isNumber() {
       boolean result =  service.isNumber(is);
    assertThat(result, is(true));
    }

    @Test
    public void name() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(input);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        service.dropAbuses(is, bos, ab);
        byte[] array = bos.toByteArray();
        for (byte b: array) {
            System.out.print((char)b);
        }
        System.out.println();
    }
}