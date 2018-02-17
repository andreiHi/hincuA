package ru.job4j.shop.service;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.shop.model.Product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product>products = new ArrayList<>();
        products.add(new Product(10, "moto","test", 1201, 120));
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
