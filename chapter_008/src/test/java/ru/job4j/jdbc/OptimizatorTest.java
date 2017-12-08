package ru.job4j.jdbc;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 03.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class OptimizatorTest {
    private Optimizator optimizator;
    String url;

    @Before
    @Ignore
    public void init() {
        optimizator = new Optimizator();
        if (System.getProperty("os.name").equals("Linux")) {
            this.url = "jdbc:sqlite:/home/andrei/java.db";
        } else {
            this.url =  "jdbc:sqlite:D:/sqlite/java.db";
        }
    }

    /**
     * Тест проверяет создание таблицы в бд и заполняет ее двумя значениями 1 и 2
     * затем получает их из бд и сравнивает с ожидаемым результатом.
     * @throws Exception ex.
     */
    @Test
    @Ignore
    public void whenWasAdedTwoElementsInTestTable() throws Exception {
        optimizator.setElement(2);
        optimizator.createTestTable();
        Connection con = DriverManager.getConnection(url);
        Statement st = con .createStatement();
        ResultSet rt = st.executeQuery("SELECT * FROM TEST");
        int[]result = new int[2];
        int count = 0;
        while (rt.next()) {
            result[count++] = rt.getInt("FIELD");
        }
        int[]ex = {1, 2};
        assertThat(result, is(ex));
    }

    /**
     * Метод для запуска программы .
     * @throws Exception ex.
     */
    @Test
    @Ignore
    public void startProgram() throws Exception {
        long time = System.currentTimeMillis();
        optimizator.startProgram();
        time = (System.currentTimeMillis() - time) / 1000;
        System.out.println(String.format("Время работы программы : %d сек.", time));
    }

    @Test
    @Ignore
    public void name() throws Exception {
        optimizator.setElement(2);
        optimizator.createTestTable();
        Document document = optimizator.createFirstXmlWithDom();
        System.out.println(document.getDocumentElement().getNodeName());
        NodeList list = document.getElementsByTagName("entry");
        System.out.println(list.getLength());
        String[]elements = new String[2];
        int count = 0;
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                elements[count] = e.getElementsByTagName("field").item(0).getTextContent();
            }
        }
        for (String element : elements) {
            System.out.println(element);
        }
    }
}