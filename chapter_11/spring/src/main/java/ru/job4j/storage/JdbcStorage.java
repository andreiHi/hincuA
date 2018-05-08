package ru.job4j.storage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.models.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class JdbcStorage implements Storage<User> {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOG = LogManager.getLogger(JdbcStorage.class);

    @Override
    public long create(User user) {
        return 0;
    }

    @Override
    public User read(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
