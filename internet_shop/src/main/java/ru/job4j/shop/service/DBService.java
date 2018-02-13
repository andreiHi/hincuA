package ru.job4j.shop.service;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 13.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class DBService {
    private BasicDataSource dataSource;
    private static final Logger LOG = LogManager.getLogger(DBService.class);
    private static final String FILE = "settings.properties";

    private DBService() {
        init();
    }

    private static class DBSer{
        private static final DBService INSTANCE = new DBService();
    }
    public static DBService getInstance() {
        return DBSer.INSTANCE;
    }
    private void init() {
        Properties pr = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILE)){
            pr.load(is);
            this.dataSource = new BasicDataSource();
            this.dataSource.setDriverClassName(pr.getProperty("db.class"));
            this.dataSource.setUrl(pr.getProperty("db.url"));
            this.dataSource.setUsername("db.user");
            this.dataSource.setPassword("db.password");
            this.dataSource.setMinIdle(100);
            this.dataSource.setMaxIdle(1000);
            LOG.debug("Server start.");
        } catch (IOException e) {
          LOG.error(e.getMessage(), e);
        }
    }
}
