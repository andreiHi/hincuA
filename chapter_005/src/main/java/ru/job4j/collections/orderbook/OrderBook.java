package ru.job4j.collections.orderbook;

import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.awt.List;
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
    private Map<String, TreeMap<Integer, Order>> orders = new HashMap<>();

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
        orders.putIfAbsent(order.getBook(), new TreeMap<Integer, Order>());
        if (flag) {
            TreeMap<Integer, Order> map = orders.get(order.getBook());
            map.put(order.getId(), order);
        } else {
            orders.get(order.getBook()).remove(order.getId());
        }
    }

    public HashMap<String, TreeSet<Order>> aggregation() {
        HashMap<String, TreeSet<Order>> result = new HashMap<>();
        for (TreeMap<Integer, Order> map : orders.values()) {

            for (Order newOrder : map.values()) {
                boolean f = false;
                result.putIfAbsent(newOrder.getBook(), new TreeSet<Order>());
                TreeSet<Order> tree = result.get(newOrder.getBook());
                if (tree.isEmpty()) {
                    tree.add(newOrder);
                } else {
                    for (Iterator<Order> iterator = tree.iterator(); iterator.hasNext(); ) {
                        Order treeOrder = iterator.next();

                        float prise = newOrder.getPrice() - treeOrder.getPrice();
                        int volume = newOrder.getVolume() - treeOrder.getVolume();
                        String treeOrderOperation = treeOrder.getOperation();
                        String orderOperation = newOrder.getOperation();

                        if (prise == 0) {
                            if (treeOrderOperation.equals(orderOperation)) {
                                int newVal = treeOrder.getVolume() + newOrder.getVolume();
                                treeOrder.setVolume(newVal);
                                f = true;
                                break;
                            }
                        }
                        if (prise >= 0) {
                            if (orderOperation.equals("BUY") && treeOrderOperation.equals("SELL")) {
                                if (volume > 0) {
                                    iterator.remove();
                                    newOrder.setVolume(volume);
                                }
                                if (volume < 0) {
                                    treeOrder.setVolume(Math.abs(volume));
                                    f = true;
                                    break;
                                }
                                if (volume == 0) {
                                    iterator.remove();
                                    f = true;
                                    break;
                                }
                            }
                        }
                        if (prise <= 0) {
                            if (orderOperation.equals("SELL") && treeOrderOperation.equals("BUY")) {
                                if (volume > 0) {
                                    iterator.remove();
                                    newOrder.setVolume(volume);
                                }
                                if (volume < 0) {
                                    treeOrder.setVolume(Math.abs(volume));
                                    f = true;
                                    break;
                                }
                                if (volume == 0) {
                                    iterator.remove();
                                    f = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!f) {
                        tree.add(newOrder);
                    }
                }
            }
        }
        return result;
    }
    public void aggrr() {
        TreeSet<Order> buy = new TreeSet<>();
        TreeSet<Order> sell = new TreeSet<>();
        for (TreeMap<Integer, Order> map : orders.values()) {
            for (Order newOrder : map.values()) {
                String newOrOperation = newOrder.getOperation();
                if (newOrOperation.equals("BUY")) {
                   buy = summ(buy, newOrder);
                } else {
                    sell = summ(sell,newOrder);
                }
            }
        }
        System.out.println("");
    }
    public TreeSet<Order> summ(TreeSet<Order> set, Order order) {
        if (set.isEmpty()) {
            set.add(order);
        } else {
            boolean f = false;
            for (Order o : set) {
                if (o.getPrice() == order.getPrice()) {
                    o.setVolume(o.getVolume() + order.getVolume());
                    f = true;
                    break;
                }
            }
            if (!f) {
                set.add(order);
            }
        }
        return set;
    }
}