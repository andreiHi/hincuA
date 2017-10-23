package ru.job4j.collections.orderBook;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Handler extends DefaultHandler {
    Order order;
    OrderBook orderBook;
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing.");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End parsing.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes att) throws SAXException {
        if (qName.equals("AddOrder")) {
            order = new Order();
            order.setBook(att.getValue(0));
            order.setOperation(att.getValue(1));
            order.setPrice(Float.valueOf(att.getValue(2)));
            order.setVolume(Integer.parseInt(att.getValue(3)));
            order.setId(Integer.parseInt(att.getValue(4)));
            orderBook.add(order.getBook(), order.getId(), order );
        } else if (qName.equals("DeleteOrder")) {
            order.setBook(att.getValue(0));
            order.setId(Integer.parseInt(att.getValue(1)));

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        order = null;
    }
}
