package ru.job4j.sql.database;

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
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d MMM yy, HH:mm");
    private static final SimpleDateFormat DATE_PREPARE = new SimpleDateFormat("d MMM yy");
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
    public Calendar prepareDate(String data) {
        Calendar calendar = Calendar.getInstance();
        if (data != null) {
            final String today = "сегодня";
            final String yesterday = "вчера";
            //if (data.startsWith(today)) {
            data = data.replaceAll(today, DATE_PREPARE.format(calendar.getTime()));
            //}
            // if (data.startsWith(yesterday)) {
            calendar.add(Calendar.DATE, -1);
            data = data.replaceAll(yesterday, DATE_PREPARE.format(calendar.getTime()));
            // }
            try {
                calendar.setTime(DATE_FORMAT.parse(data));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return calendar;
    }
}
