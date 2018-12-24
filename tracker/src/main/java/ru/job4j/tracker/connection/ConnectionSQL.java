package ru.job4j.tracker.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Класс соединения с бд.
 * @author Hincu Andrei (andreih1981@gmail.com) by 09.12.17;
 * @version $Id$
 * @since 0.1
 */
public class ConnectionSQL {
    /**
     * Имя пользователя в базе данных.
     */
    private Properties properties = new Properties();

    private static final class Holder {
        private static final ConnectionSQL INSTANCE = new ConnectionSQL();
    }
    /**
     * Соединение с бд.
     */
    public static ConnectionSQL getInstance() {
        return Holder.INSTANCE;
    }
    private  Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private ConnectionSQL() {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("tracker.properties")) {
            properties.load(stream);
            Class.forName(properties.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
