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
    private Map<String, TreeMap<Integer, Order>> orders = new HashMap<>();

    /**
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private void parseXml() throws ParserConfigurationException, IOException, SAXException {
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

    public void start() throws IOException, SAXException, ParserConfigurationException {
        this.parseXml();
        for (TreeMap<Integer, Order> map : orders.values()) {
            TreeSet<Order> buy = new TreeSet<>();
            TreeSet<Order> sell = new TreeSet<>();
            for (Order newOrder : map.values()) {
                String newOrOperation = newOrder.getOperation();
                if (newOrOperation.equals("BUY")) {
                    buy = amount(buy, newOrder);
                } else {
                    sell = amount(sell,newOrder);
                }
            }
            ArrayList<TreeSet<Order>> list = new ArrayList<>();
            aggregation(buy, sell);
            list.add(buy);
            list.add(sell);
            printOrders(list);
        }
    }

    private void printOrders(ArrayList<TreeSet<Order>> list) {
        System.out.println(list);
    }

    private void aggregation(TreeSet<Order> buy, TreeSet<Order> sell) {
        Iterator<Order> iteratorBuy = buy.iterator();
        while (iteratorBuy.hasNext()){
            Order orderBuy = iteratorBuy.next();
            for (Iterator<Order> iteratorSell = sell.iterator(); iteratorSell.hasNext(); ) {
                Order orderSell = iteratorSell.next();
                float prise = orderBuy.getPrice() - orderSell.getPrice();
                int volume = orderBuy.getVolume() - orderSell.getVolume();
                if (prise < 0) {
                    break;
                }
                if (prise >= 0) {
                    if (volume > 0) {
                        iteratorSell.remove();
                        orderBuy.setVolume(volume);
                    }
                    if (volume < 0) {
                        iteratorBuy.remove();
                        orderSell.setVolume(Math.abs(volume));
                        break;
                    }
                    if (volume == 0) {
                        iteratorBuy.remove();
                        iteratorSell.remove();
                        break;
                    }
                }
            }
        }
    }

    public TreeSet<Order> amount(TreeSet<Order> set, Order order) {
        if (set.isEmpty()) {
            set.add(order);
        } else {
            boolean found = false;
            for (Order o : set) {
                if (o.getPrice() == order.getPrice()) {
                    o.setVolume(o.getVolume() + order.getVolume());
                    found = true;
                    break;
                }
            }
            if (!found) {
                set.add(order);
            }
        }
        return set;
    }
}