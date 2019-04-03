package ru.job4j.composition;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class BusinessObjectImplTest {

    @Test
    public void name() {
        Process process = new Process(new BusinessObjectImpl("Kolea", 10));
        final List<String> list = process.prosess4("Vasea");
        list.forEach(System.out::println);
    }
}
