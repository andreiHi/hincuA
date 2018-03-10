package ru.job4j.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.job4j.model.Item;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 08.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class HibernateUtil {
    private static final Logger LOG = LogManager.getLogger(HibernateUtil.class);
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory = sessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory sessionFactory() {
        Configuration configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void factoryClose() {
        sessionFactory().close();
    }

    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<Item>items =  session.createQuery("select i from Item i where i.done = false").list();
        session.close();

        session.close();
        factoryClose();
        items.forEach(System.out::println);
    }
}
