package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class SqlStorage {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/java";
        String username ="postgres";
        String password = "5432";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
