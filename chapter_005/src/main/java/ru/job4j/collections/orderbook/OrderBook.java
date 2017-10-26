package ru.job4j.collections.orderbook;

import org.xml.sax.SAXException;

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

    public HashMap<String, TreeSet<Order>> aggregation() {
        HashMap<String, TreeSet<Order>> result = new HashMap<>();
        for (HashMap<Integer, Order> map : orders.values()) {

            for (Order order : map.values()) {
                boolean f = false;
                result.putIfAbsent(order.getBook(), new TreeSet<Order>());
                TreeSet<Order> tree = result.get(order.getBook());
                if (!tree.isEmpty()) {
                    for (Iterator<Order> iterator = tree.iterator(); iterator.hasNext(); ) {
                        Order treeOrder = iterator.next();

                         float prise = order.getPrice() - treeOrder.getPrice();
                         int volume = order.getVolume() - treeOrder.getVolume();
                        String treeOrderOperation = treeOrder.getOperation();
                        String orderOperation = order.getOperation();

                        if (prise == 0) {
                            if (treeOrderOperation.equals(orderOperation)) {
                                int newVal = treeOrder.getVolume() + order.getVolume();
                                treeOrder.setVolume(newVal);
                                f = true;
                                break;
                            } else {
                                treeOrder.setVolume(Math.abs(volume));
                                f = true;
                                break;
                            }
                        }
                        if (prise > 0) {
                            if (orderOperation.equals("BUY") && treeOrderOperation.equals("SELL")) {
                                if (volume > 0) {
                                    iterator.remove();
                                    order.setVolume(volume);
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
                        if (prise < 0) {
                            if (orderOperation.equals("SELL") && treeOrderOperation.equals("BUY")) {
                                if (volume > 0) {
                                    iterator.remove();
                                    order.setVolume(volume);
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
                        tree.add(order);
                    }

                } else {
                    tree.add(order);
                }
            }
        }
        return result;
    }
}