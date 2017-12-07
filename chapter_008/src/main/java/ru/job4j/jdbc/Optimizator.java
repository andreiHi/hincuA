package ru.job4j.jdbc;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
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
        this.connectionSqLite = new ConnectionSqLite();
        this.connection = connectionSqLite.getConnection();
    }
    public void startProgram() {
        createTestTable();
        Document document = createFirstXmlWithDom();
        writeDocument(document, xml1);
        convert();
        parsing();
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
    public Document createFirstXmlWithDom() {
        createFile(xml1);
        DocumentBuilderFactory builderFactory;
        DocumentBuilder documentBuilder;
        Document doc = null;
        try {
            builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            builderFactory.setIgnoringElementContentWhitespace(true);
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
          //  removeWhitespaceNodes(doc.getDocumentElement());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionSqLite.closeConnect();
        }
        return doc;
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
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
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

    /**
     * Метод с поьмощью xsl трансформирует 1xml в 2xml в соответствии с требованиями.
     * Сохроняет результат в файл 2.xml.
     */
    public void convert() {
        Source xmlInput = new StreamSource(new File(xml1));
        Source xslFile = new StreamSource(new File(xsl));
        createFile(xml2);
        Result xmlOutput = new StreamResult(xml2);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xslFile);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод удаляет все пробелы из дерева.
     * @param e корень дерева.
     */
    public  void removeWhitespaceNodes(Element e) {
        NodeList children = e.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
                e.removeChild(child);
            } else if (child instanceof Element) {
                removeWhitespaceNodes((Element) child);
            }
        }
    }

    /**
     *Метод парсит 2.xml и выводит полученный результат.
     */
    public void parsing() {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        Handler handler = new Handler();
        SAXParser parser;
        try {
            parser = parserFactory.newSAXParser();
            parser.parse(new File(xml2), handler);
            System.out.println(handler.getCount());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}