package ru.job4j.sql.database;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 29.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class SqlQuery {
    public static final String CREATE_AUTHOR_TABLE = "CREATE TABLE IF NOT EXISTS author(\n"
            + "  id  SERIAL PRIMARY KEY,\n"
            + "  name VARCHAR(50),\n"
            + "  url VARCHAR(100)\n"
            + ")";
    public static final String CREATE_TABLE_TEXT = "CREATE TABLE IF NOT EXISTS text_adverts(\n"
            + "  id SERIAL PRIMARY KEY ,\n"
            + "  text TEXT\n"
            + ")";
    public static final String CREATE_TABLE_ADVERTS = "CREATE TABLE IF NOT EXISTS adverts(\n"
            + "  id SERIAL PRIMARY KEY,\n"
            + "  title VARCHAR(200),\n"
            + "  url VARCHAR(100),\n"
            + "  text_id INTEGER REFERENCES text_adverts(id),\n"
            + "  id_autor INTEGER REFERENCES author(id),\n"
            + "  date TIMESTAMP WITHOUT TIME ZONE\n"
            + ")";
    public static final String SELECT_MAX_DATE = "SELECT max(date) AS max_date FROM adverts";

}
