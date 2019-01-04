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

    private Connection connection;
    public Connection getConnection() {
        getConnect();
        return connection;
    }
    private void getConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            if (connection != null) {
                System.out.println("Соединение установлено.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
