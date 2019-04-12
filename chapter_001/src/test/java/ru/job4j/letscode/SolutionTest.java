package ru.job4j.letscode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 28.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class SolutionTest {

    @Test
    public void staticTests() {
        assertEquals("no one likes this", Solution.whoLikesIt());
        assertEquals("Peter likes this", Solution.whoLikesIt("Peter"));
        assertEquals("Jacob and Alex like this", Solution.whoLikesIt("Jacob", "Alex"));
       assertEquals("Max, John and Mark like this", Solution.whoLikesIt("Max", "John", "Mark"));
        assertEquals("Alex, Jacob and 2 others like this", Solution.whoLikesIt("Alex", "Jacob", "Mark", "Max"));
        assertEquals("Alex, Jacob and 3 others like this", Solution.whoLikesIt("Alex", "Jacob", "Mark", "Max", "Sam"));
    }
}
