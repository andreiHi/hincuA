package ru.job4j.servlets.application;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.crud.SQLquery;
import ru.job4j.servlets.crud.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserStore {
    private static final Logger LOG = LogManager.getLogger(UserStore.class);

    private String file = "settings.properties";
    private Properties pr;
    private BasicDataSource dataSource;

    private UserStore() {
        initParam();
    }

    public boolean addNewUser(User user) {
        boolean add = false;
        try (final Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SQLquery.ADD_NEW_USER)) {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getName());
                ps.setString(3, user.getEmail());
                ps.setTimestamp(4, new Timestamp(user.getCreateDate().getTimeInMillis()));
                add = ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return add;
    }

    public void update(User user, String oldLogin) {
        try (final Connection connection = this.dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SQLquery.UPDATE_USER)) {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getName());
                ps.setString(3, user.getEmail());
                ps.setString(4, oldLogin);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
           LOG.error(e.getMessage(), e);
        }
    }


    private static class UserStoreHolder {
        private static final UserStore INSTANCE = new UserStore();
    }

    public static UserStore getInstance() {
        return UserStoreHolder.INSTANCE;
    }


    private void initParam() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(file)) {
            this.pr = new Properties();
            this.pr.load(in);
            this.dataSource = new BasicDataSource();
            this.dataSource.setDriverClassName(pr.getProperty("db.class"));
            this.dataSource.setUrl(pr.getProperty("db.url"));
            this.dataSource.setUsername(pr.getProperty("db.user"));
            this.dataSource.setPassword(pr.getProperty("db.password"));
            this.dataSource.setMinIdle(100);
            this.dataSource.setMaxIdle(1000);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    public List<User> selectUsers() {
        List<User>  list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SQLquery.SELECT_ALL_USERS)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setName(rs.getString("name"));
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(rs.getTimestamp("date").getTime());
                        user.setCreateDate(calendar);
                        user.setLogin(rs.getString("login"));
                        user.setEmail(rs.getString("email"));
                        list.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }
}
