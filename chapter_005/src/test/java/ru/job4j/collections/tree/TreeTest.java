package ru.job4j.collections.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

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
        tree.add("is Good", "True");
        boolean a = tree.add("True", "111");
        boolean m = tree.add("True", "11");
       // System.out.println(tree.getSize());
    }
    @Test
    public void add() throws Exception {
    }

    @Test
    public void iterator() throws Exception {
        Iterator<String> it = tree.iterator();
        int coutn = 0;
        while (it.hasNext()) {
            coutn++;
        }
        System.out.println(coutn);
    }

}