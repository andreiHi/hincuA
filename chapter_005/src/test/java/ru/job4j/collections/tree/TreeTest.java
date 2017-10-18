package ru.job4j.collections.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 18.10.17;
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {
    Tree<String>tree;
    @Before
    public void start() {
        tree = new Tree<>();
        tree.add("Hello","World");
        tree.add("Hello", "is Good");
       // boolean c = tree.contains()
    }
    @Test
    public void add() throws Exception {
    }

    @Test
    public void iterator() throws Exception {
    }

}