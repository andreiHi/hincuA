package ru.job4j.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class DB {
   private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/sql.ru";
    private String user = "postgres";
    private  String password = "5432";
    public DB() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Соединение с бд установлено.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
