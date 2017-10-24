package ru.job4j.collections.orderBook;

import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Order book.
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class OrderBook {

    private Map<Integer, Order> book1 = new HashMap<>(300000);
    private Map<Integer, Order> book2 = new HashMap<>(300000);
    private Map<Integer, Order> book3 = new HashMap<>(300000);
    private Map<Integer, Order> sortedBook1 = new TreeMap<>();


    public void parseXml() throws ParserConfigurationException, IOException, SAXException {
//        File file = new File("orders.xml");
//        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = builderFactory.newDocumentBuilder();
//        Document document = builder.parse(file);
//        System.out.println(document.getDocumentElement().getNodeName());
//        Element elementAdd = (Element)document.getDocumentElement();
//        NodeList list = (NodeList) document.getDocumentElement();
//        for (int i =0; i < list.getLength(); i++) {
//            Node n = list.item(i);
//        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Handler handler = new Handler(this);
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new File("orders.xml"), handler);
    }
    public void add(String book, int id, Order order) {
        if (book.equals("book-1")) {
            book1.put(id, order);
        } else if (book.equals("book-2")) {
            book2.put(id, order);
        } else  if (book.equals("book-3")){
            book3.put(id, order);
        }
    }
    public void delete(int id, String book) {
        if (book.equals("book-1")) {
            book1.remove(id);
        } else if (book.equals("book-2")) {
            book2.remove(id);
        } else  if (book.equals("book-3")){
            book3.remove(id);
        }
    }
}
