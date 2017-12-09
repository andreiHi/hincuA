package ru.job4j.tracker.connection;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 09.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class  Query {
    public static final String CREATE_TRACKER_TABLE = "CREATE TABLE IF NOT EXISTS tracker(\n"
            + "  id SERIAL PRIMARY KEY NOT NULL ,\n"
            + "  name VARCHAR(50),\n"
            + "  description VARCHAR(100),\n"
            + "  create_date TIMESTAMP WITHOUT TIME ZONE\n"
            + ")";
    public static final String INSERT = "INSERT INTO tracker(name, description, create_date) VALUES (?, ?, ?)";
    public static final String SELECT_ALL = "SELECT * FROM tracker";
    public static final String SELECT_BY_ID = "SELECT * FROM tracker WHERE id= ?";
}
