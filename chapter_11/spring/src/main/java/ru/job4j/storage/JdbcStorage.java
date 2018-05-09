package ru.job4j.storage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.models.User;
import ru.job4j.models.UserMapper;

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
        String sql = "INSERT INTO USERS (name, age) VALUES(?, ?)";
        this.jdbcTemplate.update(sql, user.getName(), user.getAge());
        System.out.println(user + "was saved successful");
        return 0;
    }

    @Override
    public User read(long id) {
        String sql = "SELECT * FROM users AS u WHERE u.id = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
        return user;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE users  SET name =?, age=? WHERE id =?";
        return   jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getId()) > 0;
    }

    @Override
    public boolean delete(User user) {
        String sql = "DELETE  FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, user.getId()) > 0;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE  FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM uasers";
        this.jdbcTemplate.update(sql);
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(50), age INT)";
        this.jdbcTemplate.execute(sql);
    }
}
