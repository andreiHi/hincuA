package ru.job4j.servlets.crud;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserStore {

    private static final Logger LOG = LogManager.getLogger(UserStore.class);
    private static final UserStore INSTANCE = new UserStore();
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
            createTable();
            LOG.debug("Соединение с бд установлено.");
        } catch (SQLException | ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    public void createTable() {
        try (final Statement st = this.connection.createStatement()) {
            st.executeUpdate(SQLquery.CREATE_TABLE_USERS);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
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
    public static UserStore getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().getTime());
    }

    public void addNewUser(User user) {
        try (PreparedStatement ps = this.connection.prepareStatement(SQLquery.ADD_NEW_USER)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, new Timestamp(user.getCreateDate().getTimeInMillis()));
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
