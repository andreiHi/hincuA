package ru.job4j.html.service;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.html.model.Address;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 04.02.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class DBConnection {
    private static final Logger LOG = LogManager.getLogger(DBConnection.class);
    private String file = "settings.properties";
    private Properties pr;
    private BasicDataSource dataSource;

    private DBConnection() {
        initParam();
        createTables();
    }

    public List<Address> getAllTowns() {
        List<Address> list = new ArrayList<>();
        return list;
    }

    private static class DBConnHolder {
        private static final DBConnection INSTANCE = new DBConnection();
    }
    public static DBConnection getInstance() {
        return DBConnHolder.INSTANCE;
    }
    private void initParam() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(file)) {
            this.pr = new Properties();
            this.pr.load(is);
            this.dataSource = new BasicDataSource();
            this.dataSource.setDriverClassName(pr.getProperty("db.class"));
            this.dataSource.setUrl(pr.getProperty("db.url"));
            this.dataSource.setUsername(pr.getProperty("db.user"));
            this.dataSource.setPassword(pr.getProperty("db.password"));
            this.dataSource.setMaxIdle(1000);
            this.dataSource.setMinIdle(100);

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    public void close() {
        try {
            this.dataSource.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    public List<Address> getAllCountries() {
        List<Address> list = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection();
             final Statement st = connection.createStatement();
             final ResultSet rs = st.executeQuery(SQLQuery.GET_ALL_COUNTRIES)
        ) {
            while (rs.next()) {
                list.add(new Address(rs.getString("id"), rs.getString("country")));
            }
        } catch (SQLException e) {
        LOG.error(e.getMessage(), e);
        }
        return list;
    }
    private void createTables() {
        try (final Connection connection = dataSource.getConnection();
             final Statement st = connection.createStatement()
        ) {
            st.executeUpdate(SQLQuery.CREATE_TABLE_ROLE);
            st.executeUpdate(SQLQuery.CREATE_TABLE_USERS);
            st.executeUpdate(SQLQuery.CREATE_TABLE_COUNTRIES);
            st.executeUpdate(SQLQuery.CREATE_TABLE_TOWNS);
            try (final ResultSet rs = st.executeQuery(SQLQuery.SELECT_ALL_USERS)) {
                if (!rs.next()) {
                    st.executeUpdate(SQLQuery.CREATE_ROLES);
                    st.executeUpdate(SQLQuery.CREATE_ROOT_USER);
                }
            }
            try (ResultSet rs = st.executeQuery(SQLQuery.GET_ALL_COUNTRIES)) {
                if (!rs.next()) {
                    st.executeUpdate(SQLQuery.ADD_COUNTIES);
                }
            }
        } catch (SQLException e) {
           LOG.error(e.getMessage(), e);
        }
    }
}
