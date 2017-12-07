package ru.job4j.jdbc;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

/**
 * .
 * @author Hincu Andrei (andreih1981@gmail.com) by 03.12.17;
 * @version $Id$
 * @since 0.1
 */
public class Optimizator {
    private final int element = 1000;
    private Connection connection;
    private ConnectionSqLite connectionSqLite;
    private final String xml1 = "1.xml";
    private final String xml2 = "2.xml";
    private final String xsl = "converter.xsl";

    public Optimizator() {
        connectionSqLite = new ConnectionSqLite();
        connection = connectionSqLite.getConnection();
    }
    public void startProgram() {
        createTestTable();
        createFirstXmlWithDom();
    }
    public static void main(String[] args) {
        Optimizator optimizator = new Optimizator();
        optimizator.createTestTable();
//        // load the sqlite-JDBC driver using the current class loader
//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Connection connection = null;
//        try {
//            // create a database connection
//            connection = DriverManager.getConnection("jdbc:sqlite:D:/sqlite/java.db");
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);  // set timeout to 30 sec.
//
//            int i = statement.executeUpdate("DROP TABLE IF EXISTS person");
//            statement.executeUpdate("CREATE TABLE person (id INTEGER, name STRING)");
//            statement.executeUpdate("INSERT INTO person VALUES(1, 'leo')");
//            statement.executeUpdate("INSERT INTO person VALUES(2, 'yui')");
//            ResultSet rs = statement.executeQuery("SELECT * FROM person");
//            while (rs.next()) {
//                // read the result set
//                System.out.println("name = " + rs.getString("name"));
//                System.out.println("id = " + rs.getInt("id"));
//            }
//        } catch (SQLException e) {
//            // if the error message is "out of memory",
//            // it probably means no database file is found
//            System.err.println(e.getMessage());
//        }
    }
    public void createTestTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS TEST");
            statement.executeUpdate("CREATE TABLE TEST(FIELD int)");
            insert(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Connection con) {
        try {
            con.setAutoCommit(false);
            PreparedStatement statement = con.prepareStatement("INSERT INTO TEST (FIELD) VALUES (?)");
            for (int i = 1; i < element + 1; i++) {
                statement.setInt(1, i);
                statement.addBatch();
            }
            statement.executeBatch();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void createFirstXmlWithDom() {
        createFile(xml1);
        DocumentBuilderFactory builderFactory;
        DocumentBuilder documentBuilder;
        Document doc;
        try {
            builderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = builderFactory.newDocumentBuilder();
            doc = documentBuilder.newDocument();

            Element root = doc.createElement("entries");
            doc.appendChild(root);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM TEST");
            while (rs.next()) {
                Element entry = doc.createElement("entry");
                root.appendChild(entry);
                Element field = doc.createElement("field");
                field.setTextContent(rs.getString("FIELD"));
                entry.appendChild(field);
            }
            writeDocument(doc, xml1);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createFile(String path) {
        File xml = new File(path);
        try {
            boolean created = xml.createNewFile();
            if (created) {
                System.out.println(String.format("Файл %s создан.", path));
            } else {
                System.out.println(String.format("Файл %s уже существует и будет перезаписан.", path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDocument(Document document, String path) {
        Transformer transformer;
        DOMSource domSource;
        FileOutputStream fos;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            domSource = new DOMSource(document);
            fos = new FileOutputStream(path);
            StreamResult streamResult = new StreamResult(fos);
            //две строки которые преобразуют хмл к выводу в столбик, а не в строчку
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //делает отступ от края страницы
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(domSource, streamResult);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    public void convert() {
        Source xmlInput = new StreamSource(new File(xml1));
        Source xslFile = new StreamSource(new File(xsl));
        createFile(xml2);
        Result xmlOutput = new StreamResult(xml2);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xslFile);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


}