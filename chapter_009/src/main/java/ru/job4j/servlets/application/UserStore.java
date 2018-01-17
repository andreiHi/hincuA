package ru.job4j.servlets.application;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.crud.SQLquery;
import ru.job4j.servlets.crud.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
            e.printStackTrace();
        }
        return add;
    }

//    private static final Object SYNC = new Object();
//    private static volatile UserStore userStore;
//    public static UserStore getUserStore() {
//        if (userStore == null) {
//            synchronized (SYNC) {
//                if (userStore == null) {
//                    userStore = new UserStore();
//                }
//            }
//        }
//        return userStore;
//    }

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

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
