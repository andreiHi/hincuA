package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 03.12.17;
 * @version $Id$
 * @since 0.1
 */
public class Optimizator {
    private final String url = "jdbc:sqlite:/home/andrei/java.db";
    public static void main(String[] args) {
        Optimizator optimizator = new Optimizator();

    }
    public void createTestTable() {
        Connection conn;
        try {
         conn = DriverManager.getConnection(url);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
