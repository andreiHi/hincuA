package ru.job4j.collections.orderbook;

import org.xml.sax.SAXException;
import ru.job4j.collections.tree.Tree;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Order book.
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class OrderBook {
    /**
     * Хранилище книг.
     */
    private Map<String, HashMap<Integer, Order>> orders = new HashMap<>();

    /**
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void parseXml() throws ParserConfigurationException, IOException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Handler handler = new Handler(this);
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new File("orders.xml"), handler);
    }

    public void addOrRemove(Order order, boolean flag) {
        orders.putIfAbsent(order.getBook(), new HashMap<Integer, Order>());
        if (flag) {
            HashMap<Integer, Order> map = orders.get(order.getBook());
            map.put(order.getId(), order);
        } else {
            orders.get(order.getBook()).remove(order.getId());
        }
    }

    public void agregate() {
        for (HashMap<Integer, Order> map : orders.values()) {

        }

    }
}