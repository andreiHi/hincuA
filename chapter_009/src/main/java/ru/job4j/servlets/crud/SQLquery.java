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
            + "  login VARCHAR(100),\n"
            + "  name VARCHAR(100),\n"
            + "  email VARCHAR(100),\n"
            + "  date TIMESTAMP\n"
            + ")";
    public static final String ADD_NEW_USER ="INSERT INTO users (login, name, email, date) VALUES (?, ?, ?, ?)";
}
