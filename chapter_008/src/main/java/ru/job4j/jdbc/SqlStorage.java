package ru.job4j.jdbc;

import java.sql.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class SqlStorage {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/java";
        String username = "postgres";
        String password = "5432";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
           // Statement st = conn.createStatement();
            //ResultSet rs = st.executeQuery("SELECT * FROM users;");
            PreparedStatement st = conn.prepareStatement("SELECT * FROM users AS u WHERE u.id IN (?, ?, ?)");
            st.setInt(1,1);
            st.setInt(2,2);
            st.setInt(3, 3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%s %s %s %s",rs.getString("id"), rs.getString("name"), rs.getString("login"), rs.getString("password")));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
