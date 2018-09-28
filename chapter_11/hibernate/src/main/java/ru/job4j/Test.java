package ru.job4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    private static final Logger LOG = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydbtest?useSSL=false", "root","password");
        } catch (SQLException e) {
            LOG.info(e.getMessage(), e);
        }
    }
}
