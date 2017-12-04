package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class ConnectionSqLite {
    private final String driver = "org.sqlite.JDBC";
    private final String url =  "jdbc:sqlite:D:/sqlite/java.db";
    private Connection connection;
    public Connection getConnection() {
        getConnect();
        return connection;
    }
    private void getConnect() {
        try {
            Class.forName(driver);
            System.out.println("Соединение установлено.");
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void closeConnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
