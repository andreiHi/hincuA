package ru.job4j.models;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.05.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UserMapper implements RowMapper<User> {
    private static final Logger LOG = LogManager.getLogger(UserMapper.class);

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setAge(rs.getInt("age"));
        return user;
    }
}
