package ru.job4j.litle.converter;

import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.07.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("middle", 8));
        queue.put(new Task("middle", 9));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

}