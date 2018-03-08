package ru.job4j.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.model.Item;

import java.sql.Timestamp;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class Service {
    private static final Logger LOG = LogManager.getLogger(Service.class);

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setDescription("ddddd");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(true);
        session.save(item);
        //System.out.println(session.createQuery("from User").list());
        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
