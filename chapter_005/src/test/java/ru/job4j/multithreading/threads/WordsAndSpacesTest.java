package ru.job4j.multithreading.threads;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.11.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class WordsAndSpacesTest {
    @Test
    public void name() throws Exception {
        WordsAndSpaces words = new WordsAndSpaces();
        words.wordsAndSpaces();
    }
}