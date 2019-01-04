package ru.job4j.servlets.application.controller;


import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlets.application.service.UserStorage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.12.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class PoolRollback extends BasicDataSource {
    public PoolRollback() {
        Properties settings = new Properties();
        try (InputStream stream = UserStorage.class.getClassLoader().getResourceAsStream("gradle.properties")) {
            settings.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.setDriverClassName(settings.getProperty("db.driver"));
        super.setUrl(settings.getProperty("db.host"));
        super.setUsername(settings.getProperty("db.login"));
        super.setPassword(settings.getProperty("db.password"));
        super.setMinIdle(5);
        super.setMaxIdle(10);
        super.setMaxOpenPreparedStatements(100);

    }

    @Override
    public Connection getConnection() throws SQLException {
        return ConnectionRollback.create(super.getConnection());
    }
}
