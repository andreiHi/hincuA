package ru.job4j.sql;


import ru.job4j.sql.database.DB;
import ru.job4j.sql.items.Advert;

import java.sql.Connection;
import java.util.concurrent.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class Sql {
    private DB db;
    private Connection dbConnection;
    private ArrayBlockingQueue<Advert> queue;

    public Sql(DB db) {
        this.db = db;
        this.dbConnection = db.getConnection();
        this.queue = new ArrayBlockingQueue<Advert>(500);
    }

    public static void main(String[] args) throws InterruptedException {
        DB db = new DB();
        Sql sql = new Sql(db);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        PageParser pageParser = new PageParser(sql.queue, sql.db);
        service.scheduleWithFixedDelay(pageParser, 0, 1, TimeUnit.DAYS);
        service.scheduleWithFixedDelay(new AdvertScanner(sql.queue, sql.db), 0, 1, TimeUnit.DAYS);
        service.scheduleWithFixedDelay(new AdvertScanner(sql.queue, sql.db), 0, 1, TimeUnit.DAYS);
        service.scheduleWithFixedDelay(new AdvertScanner(sql.queue, sql.db), 0, 1, TimeUnit.DAYS);

    }


}
