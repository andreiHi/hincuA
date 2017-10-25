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
        saxParser.parse(new File("order.xml"), handler);
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

    public HashMap<String, TreeSet<Order>> agregate() {
        HashMap<String, TreeSet<Order>>result = new HashMap<>();
        for (HashMap<Integer, Order> map : orders.values()) {
            for (Order order : map.values()) {
                result.putIfAbsent(order.getBook(), new TreeSet<Order>());
                TreeSet<Order> tree = result.get(order.getBook());
                if (!tree.isEmpty()) {
                    for (Order treeOrder : tree) {
                        if (treeOrder.getPrice() == order.getPrice()) {
                            if (treeOrder.getOperation().equals(order.getOperation())) {
                                treeOrder.setVolume(treeOrder.getVolume() + order.getVolume());
                            } else {
                                if (treeOrder.getVolume() == order.getVolume()) {
                                    tree.remove(treeOrder);
                                    break;
                                } else {
                                    int val =  order.getVolume() - treeOrder.getVolume();
                                    if (val > 0) {
                                        tree.remove(treeOrder);
                                        order.setVolume(val);
                                        tree.add(order);
                                        break;
                                    } else {
                                        treeOrder.setVolume(Math.abs(val));
                                        break;
                                    }
                                }
                            }
                        } else if () {
                        }
                    }
                } else {
                    tree.add(order);
                }
            }
        }
        return result;
    }
}