package ru.job4j.collections.orderBook;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class OrderBookTest {
    OrderBook orderBook;
    @Before
    public void start() {
        orderBook = new OrderBook();
    }

    @Test
    public void name() throws Exception {
    orderBook.parseXml();
    }
}