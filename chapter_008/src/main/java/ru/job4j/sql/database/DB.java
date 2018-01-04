package ru.job4j.sql.database;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.sql.Sql;
import ru.job4j.sql.items.Advert;
import ru.job4j.sql.items.Author;

import java.sql.*;

/**
 * Класс бд подключения и запросов.
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class DB {

    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/sql.ru";
    private String user = "postgres";
    private  String password = "5432";
    private static final Logger LOG = LogManager.getLogger(Sql.class);

    public DB() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Соединение с бд установлено.");
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Connection ERROR", e);
        }
    }

    /**
     * Метод создает таблицы и функции в бд если их еще нет.
     */
    public void createTables() {
        try (final Statement statement = connection.createStatement()) {
            statement.executeUpdate(SqlQuery.CREATE_AUTHOR_TABLE);
            statement.executeUpdate(SqlQuery.CREATE_TABLE_ADVERTS);
            statement.executeUpdate(SqlQuery.CREATE_FUNCTION_ADD_AUTHOR);
            statement.executeUpdate(SqlQuery.CREATE_FUNCTION_ADD_ADVERT);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Error when was create table and functions", e);
        }
    }

    /**
     * Метод возвращает дату последнего обновления в милисекундах.
     * @return 0 если бд пустая или дата последнего обновления.
     */
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
            LOG.error("Query error", e);
        }
        return dataMax;
    }

    /**
     * Метод сохраняет обьявление в бд и записывает в лог id и само объявление.
     * @param advert объявление.
     */
    public void addNewAdvert(Advert advert) {
        try (PreparedStatement ps = this.connection.prepareStatement(SqlQuery.ADD_ADVERT)) {
            Author author = advert.getAuthor();
            ps.setString(1, author.getName());
            ps.setString(2, author.getUrl());
            ps.setString(3, advert.getTitle());
            ps.setString(4, advert.getUrl());
            ps.setString(5, advert.getText());
            ps.setTimestamp(6, new Timestamp(advert.getPublicationDate().getTimeInMillis()));
            ps.setTimestamp(7, new Timestamp(advert.getDate().getTimeInMillis()));
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("add_advert");
                LOG.debug(advert + "was saved with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Error when advert was add in db", e);
        }
    }
}
