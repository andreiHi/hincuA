package ru.job4j.shop.service;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.shop.model.Product;
import ru.job4j.shop.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class DBService {
    private Properties sqlQuery;
    private BasicDataSource dataSource;
    private static final Logger LOG = LogManager.getLogger(DBService.class);
    private static final String FILE = "settings.properties";
    private static final String SQL_FILE = "sql.properties";

    private DBService() {
        init();
        createTables();
    }

    public boolean checkLogin(String login) {
        boolean exist = true;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("SELECT_LOGIN"))
        ) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exist = false;
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return exist;
    }

    public boolean addNewUser(User user) {
        boolean add = false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("ADD_NEW_USER"))) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, "user");
            add = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return add;
    }

    public boolean checkUser(String login, String password) {
        boolean exist = false;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty("GET_USER_BY_PASS_AND_LOGIN"))
        ) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    private static class DBSer {
        private static final DBService INSTANCE = new DBService();
    }
    public static DBService getInstance() {
        return DBSer.INSTANCE;
    }
    private void init() {
        this.sqlQuery = new Properties();
        Properties pr = new Properties();
        try (InputStream isDb = getClass().getClassLoader().getResourceAsStream(FILE);
             InputStream isSql = getClass().getClassLoader().getResourceAsStream(SQL_FILE)
        ) {
            pr.load(isDb);
            this.dataSource = new BasicDataSource();
            this.dataSource.setDriverClassName(pr.getProperty("db.class"));
            this.dataSource.setUrl(pr.getProperty("db.url"));
            this.dataSource.setUsername(pr.getProperty("db.user"));
            this.dataSource.setPassword(pr.getProperty("db.password"));
            this.dataSource.setMinIdle(100);
            this.dataSource.setMaxIdle(1000);
            this.sqlQuery.load(isSql);
            LOG.debug("Server start.");
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    private void createTables() {
        try (Connection connection = this.dataSource.getConnection();
             Statement st = connection.createStatement()
        ) {
            st.executeUpdate(sqlQuery.getProperty("CREATE_TABLE_PRODUCTS"));
            st.executeUpdate(sqlQuery.getProperty("CREATE_TABLE_ROLE"));
            st.executeUpdate(sqlQuery.getProperty("CREATE_TABLE_USERS"));
            try (ResultSet rs = st.executeQuery(sqlQuery.getProperty("SELECT_ALL_FROM_USERS"))) {
                if (!rs.next()) {
                    st.executeUpdate(sqlQuery.getProperty("INSERT_ROLE"));
                    st.executeUpdate(sqlQuery.getProperty("INSERT_ROOT_USER"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts(String sort) {
        List<Product> products = new ArrayList<>();
        try {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sqlQuery.getProperty(sort));
                 ResultSet rs = ps.executeQuery()
            ) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setMiniDescription(rs.getString("minidescription"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getInt("price"));
                    product.setAmount(rs.getInt("amount"));
                    product.setViews(rs.getInt("views"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return products;
    }
    public void close() {
        try {
            this.dataSource.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
