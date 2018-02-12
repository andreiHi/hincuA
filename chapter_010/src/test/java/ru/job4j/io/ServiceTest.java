package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 12.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ServiceTest {
    private String file = "number.txt";
    private InputStream is;
    private Service service;

    @Before
    public void init() {
        service = new Service();
        is = getClass().getClassLoader().getResourceAsStream(file);
    }
    @Test
    public void isNumber() {
       boolean result =  service.isNumber(is);
    assertThat(result, is(true));
    }

}