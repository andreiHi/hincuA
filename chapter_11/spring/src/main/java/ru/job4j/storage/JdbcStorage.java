package ru.job4j.storage;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.models.User;
import ru.job4j.models.UserMapper;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 07.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Repository
public class JdbcStorage implements Storage<User> {
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOG = LogManager.getLogger(JdbcStorage.class);

    @Autowired
    public JdbcStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(User user) {
        final String sql = "INSERT INTO USERS (name, age) VALUES(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            return ps;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
        LOG.info(user + "was saved successful with id " + user.getId());
        return user.getId();
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
        String sql = "DELETE FROM users";
        this.jdbcTemplate.update(sql);
    }


    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(50), age INT)";
        this.jdbcTemplate.execute(sql);
    }

}
