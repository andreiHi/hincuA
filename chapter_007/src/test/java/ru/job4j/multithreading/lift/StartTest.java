package ru.job4j.multithreading.lift;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.Assert.*;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 12.01.18;
 * @version $Id$
 * @since 0.1
 */
public class StartTest {
    Lift lift;
    Input input;
    ArrayBlockingQueue<Integer> in;
    ArrayBlockingQueue<Integer> ex;
    @Before
    public void init() {
         this.in = new ArrayBlockingQueue<Integer>(20);
       this.ex = new ArrayBlockingQueue<Integer>(20);
        this.lift = new Lift("20", "5", "4", "3", ex, in);

    }

    @Test
    public void name() throws Exception {
        String[]ans = {"3", "L", "5", "0"};
        this.input = new ImputForTest(in, ex, "20", ans);
        Start start = new Start(lift, input);
        start.start();

    }
}