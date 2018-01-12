package ru.job4j.servlets.crud;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserStore {

    private static final Logger LOG = LogManager.getLogger(UserStore.class);
    private static final UserStore ISTANCE = new UserStore();
    private Connection connection;
    private String login;
    private String password;
    private String url;
    private String file = "settings.properties";

    private UserStore() {
        try {
            initParam();
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, login, password);
            LOG.debug("Соединение с бд установлено.");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void initParam() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(file)) {
            Properties pr = new Properties();
            pr.load(in);
            this.url = pr.getProperty("db.url");
            this.login = pr.getProperty("db.user");
            this.password = pr.getProperty("db.password");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    public static UserStore getIstance() {
        return ISTANCE;
    }

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().getTime());
    }
}
