package ru.job4j.collections.orderbook;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

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
    orderBook.aggregation();
    }

    @Test
    public void print() throws Exception {
        TreeSet<Order> set = new TreeSet<>();
        Order order = new Order("BUY", 100.10f, 50);
        Order order1 = new Order("BUY", 100.20f, 75);
        Order order2 = new Order("SELL", 100.10f, 50);
        Order order3 = new Order("SELL", 100.20f, 75);
        set.add(order3);
        set.add(order2);
        set.add(order1);
        set.add(order);
        for (Order o : set) {
            System.out.println(o);
        }

    }
        @Test
    public void print1() throws Exception {
        orderBook.parseXml();

        HashMap<String, TreeSet<Order>> map = orderBook.aggregation();
        for (Map.Entry<String, TreeSet<Order>> m : map.entrySet()) {
            String bookName = m.getKey();
            TreeSet<Order> ordersToPrint = m.getValue();
            System.out.println(bookName);
            for (Order order : ordersToPrint) {
                System.out.println(order);
            }
        }


    }
}