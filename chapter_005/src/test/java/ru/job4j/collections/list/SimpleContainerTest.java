package ru.job4j.collections.list;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 06.10.17;
 * @version $Id$
 * @since 0.1
 */
public class SimpleContainerTest {
    @Test
    public void name() throws Exception {
    SimpleContainer<String>strings = new SimpleContainer<>();
    strings.add("Ivanov");
    strings.add("Petrov");
    strings.add("Sidorov");
    strings.add("Vasichkin");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}