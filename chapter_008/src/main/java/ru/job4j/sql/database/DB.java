package ru.job4j.sql.database;

import java.sql.*;
import java.util.Calendar;

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

    public void createTables() {
        try (final Statement statement = connection.createStatement()) {
            statement.executeUpdate(SqlQuery.CREATE_AUTHOR_TABLE);
            statement.executeUpdate(SqlQuery.CREATE_TABLE_TEXT);
            statement.executeUpdate(SqlQuery.CREATE_TABLE_ADVERTS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public long getLastTimeOfUpdate() {
        long data_max = 0;
        try (final Statement st = this.connection.createStatement()) {
            ResultSet rs = st.executeQuery(SqlQuery.SELECT_MAX_DATE);
            if (rs.next()) {
                if (rs.getTimestamp("max_date") != null) {
                    data_max = rs.getTimestamp("max_date").getTime();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data_max;
    }
}
