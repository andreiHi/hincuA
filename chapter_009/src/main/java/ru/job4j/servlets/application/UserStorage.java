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
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserStorage {
    private static final Logger LOG = LogManager.getLogger(UserStorage.class);

    private String file = "settings.properties";
    private Properties pr;
    private BasicDataSource dataSource;

    private UserStorage() {
        initParam();
        createTables();
    }

    /**
     * Метод добавляет нового пользователя.
     * @param user пользователь.
     * @return успешно или нет.
     */
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

    /**
     * Метод обновляет данные пользователя.
     * @param user пользователь с новыми данными.
     * @param oldLogin логин обновляемого пользователя.
     */
    public void update(User user, String oldLogin) {
        try (final Connection connection = this.dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SQLquery.UPDATE_USER)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getLogin());
                ps.setString(3, user.getEmail());
                ps.setString(4, oldLogin);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод удалят пользователя.
     * @param login логин пользователя.
     */
    public void deleteUser(String login) {
        try (final Connection connection = this.dataSource.getConnection()) {
            try (final PreparedStatement ps = connection.prepareStatement(SQLquery.DELETE_USER)) {
                ps.setString(1, login);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public User getUser(String login) {
        User user = null;
        try (Connection connection = this.dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SQLquery.GET_USER_BY_LOGIN)) {
                ps.setString(1, login);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        user = new User();
                        user.setId(rs.getString("id"));
                        user.setLogin(rs.getString("login"));
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(rs.getTimestamp("date").getTime());
                        user.setCreateDate(calendar);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void close() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }


    private static class UserStoreHolder {
        private static final UserStorage INSTANCE = new UserStorage();
    }

    public static UserStorage getInstance() {
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

    private void createTables() {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement st = connection.createStatement()) {
                st.executeUpdate(SQLquery.CREATE_TABLE_ROLE);
                st.executeUpdate(SQLquery.CREATE_TABLE_USERS);
                try (ResultSet rs = st.executeQuery(SQLquery.SELECT_ALL_USERS)) {
                    if (!rs.next()) {
                        st.executeUpdate(SQLquery.CREATE_ROLES);
                        st.executeUpdate(SQLquery.CREATE_ROOT_USER);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод получения всех пользователей.
     * @return лит пользователей.
     */
    public List<User> selectUsers() {
        List<User>  list = new CopyOnWriteArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SQLquery.SELECT_ALL_USERS)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getString("id"));
                        user.setName(rs.getString("name"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(rs.getString("role"));
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
    public boolean isCredential(String login, String password) {
        boolean exist = false;
        List<User> list = selectUsers();
        for (User user : list) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                exist = true;
                break;
            }
        }
        List list1 = new ArrayList<Integer>();
        list1.add(new Object());
        list1.add(111);
        list1.add("111");
        System.out.println(list1.size());
        return exist;


    }
}
