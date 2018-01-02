package ru.job4j.sql;

import ru.job4j.sql.database.DB;
import ru.job4j.sql.items.Advert;
import ru.job4j.sql.items.Author;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 25.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Test {
    ArrayBlockingQueue<Advert> queue;


    public static void main(String[] args) {
            DB db = new DB();
            db.createTables();
            Advert advert = new Advert();
            Author author = new Author("asasa", "dadsd");
            advert.setAuthor(author);
            advert.setText("sdasdsdasdd");
            db.addNewAdvert(advert);


        }


    }
