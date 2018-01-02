package ru.job4j.sql.database;

import ru.job4j.sql.items.Advert;
import ru.job4j.sql.items.Author;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        long dataMax = 0;
        try (final Statement st = this.connection.createStatement()) {
            ResultSet rs = st.executeQuery(SqlQuery.SELECT_MAX_DATE);
            if (rs.next()) {
                if (rs.getTimestamp("max_date") != null) {
                    dataMax = rs.getTimestamp("max_date").getTime();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataMax;
    }

    public void addNewAdvert(Advert advert) {
        try (final PreparedStatement ps = this.connection.prepareStatement(SqlQuery.SELECT_TEXT)) {
            ps.setString(1, advert.getText());
           ResultSet rs =  ps.executeQuery();
           int idText;
           int idAuthor;
            if (!rs.next()) {
                try (PreparedStatement preparedStatement = this.connection.prepareStatement(SqlQuery.INSERT_TEXT,
                                                                           PreparedStatement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, advert.getText());
                    preparedStatement.executeUpdate();
                    ResultSet set = preparedStatement.getGeneratedKeys();
                    while (set.next()) {
                        idText = set.getInt(1);
                        System.out.println(idText);
                    }
                }
                try (PreparedStatement preparedStatement = this.connection.prepareStatement(SqlQuery.INSERT_AUTHOR,
                                                                           PreparedStatement.RETURN_GENERATED_KEYS)) {
                    Author author = advert.getAuthor();
                    preparedStatement.setString(1, author.getName());
                    preparedStatement.setString(2, author.getUrl());
                    preparedStatement.executeUpdate();
                    ResultSet set = preparedStatement.getGeneratedKeys();
                    while (set.next()) {
                        idAuthor = set.getInt(1);
                        System.out.println(idAuthor);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
