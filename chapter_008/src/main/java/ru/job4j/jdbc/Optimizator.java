package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * .
 * @author Hincu Andrei (andreih1981@gmail.com) by 03.12.17;
 * @version $Id$
 * @since 0.1
 */
public class Optimizator {
    private final int element = 1000000;
    private Connection connection;
    private ConnectionSqLite connectionSqLite;

    public Optimizator() {
        connectionSqLite = new ConnectionSqLite();
        connection = connectionSqLite.getConnection();
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
            insert(connection, element);
//            ResultSet rs = statement.executeQuery("SELECT * FROM TEST");
//            while (rs.next()) {
//                System.out.println(rs.getString("FIELD"));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(Connection con, int value) {
        try {
            con.setAutoCommit(false);
            for (int i = 1; i < value; i += 1000) {
                PreparedStatement statement = con.prepareStatement("INSERT INTO TEST (FIELD) VALUES (?)");
                for (int j = i; j < i + 1000; j++) {
                    statement.setInt(1, j);
                    //собираем в буфер
                    statement.addBatch();
                }
                //добовляем весь буфер сразу
                statement.executeBatch();
            }
            con.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}