package ru.job4j.servlets.crud;

/**
 * .
 *
 * @author Hincu Andrei (andreih1981@gmail.com) by 13.01.18;
 * @version $Id$
 * @since 0.1
 */
public class SQLquery {

    public static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users("
            + "  id SERIAL PRIMARY KEY ,"
            + "  login VARCHAR(100)  UNIQUE,"
            + "  name VARCHAR(100),"
            + "  email VARCHAR(100),"
            + "  date TIMESTAMP,"
            + "  role INT REFERENCES role(id),"
            + "   password VARCHAR(20)"
            + ")";
    public static final String CREATE_TABLE_ROLE = "CREATE TABLE IF NOT EXISTS role("
            + "  id SERIAL PRIMARY KEY ,"
            + "  role VARCHAR(20)"
            + ")";
    public static final String ADD_NEW_USER = "INSERT INTO users (login, name, email, date) VALUES (?, ?, ?, ?)";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String UPDATE_USER = "UPDATE users SET name = ?, login = ?, email = ? WHERE login = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE login = ?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM users LEFT JOIN role ON users.role=role.id";
    public static final String CREATE_ROLES = "INSERT INTO role (role)  VALUES ('admin'), ('user')";
    public static final String CREATE_ROOT_USER = "INSERT INTO users(login, role, password, date) VALUES ('root', 1, 'root' ,now())";


}
