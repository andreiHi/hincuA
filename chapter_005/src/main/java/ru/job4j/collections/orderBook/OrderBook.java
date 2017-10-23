package ru.job4j.collections.orderBook;

import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Order book.
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.10.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class OrderBook {

    Map<Integer, Order> book1 = new HashMap<>();
    Map<Integer, Order> book2 = new HashMap<>();
    Map<Integer, Order> book3 = new HashMap<>();


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
        Handler handler = new Handler();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new File("orders.xml"), handler);
    }
    public void add(String book, int id, Order order) {
        if (book.equals("book1")) {

        } else if (book.equals("book2")) {

        } else {

        }
    }
    public void delete() {

    }
}
