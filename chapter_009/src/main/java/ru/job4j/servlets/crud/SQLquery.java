package ru.job4j.servlets.crud;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 13.01.18;
 * @version $Id$
 * @since 0.1
 */
public class SQLquery {

    public static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users(\n"
            + "  id SERIAL PRIMARY KEY ,\n"
            + "  login VARCHAR(100)  UNIQUE,\n"
            + "  name VARCHAR(100),\n"
            + "  email VARCHAR(100),\n"
            + "  date TIMESTAMP\n"
            + ")";
    public static final String ADD_NEW_USER = "INSERT INTO users (login, name, email, date) VALUES (?, ?, ?, ?)";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String UPDATE_USER = "UPDATE users SET name = ?, login = ?, email = ? WHERE login = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE login = ?";
    public static final String SELECT_ALL_USERS = "Select * FROM users";

}
