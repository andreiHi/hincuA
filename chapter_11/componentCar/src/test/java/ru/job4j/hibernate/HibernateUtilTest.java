package ru.job4j.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Car;
import ru.job4j.model.Engine;
import ru.job4j.model.Transmission;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 12.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class HibernateUtilTest {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Before
    public void start() {
      setTransmission();

    }
    @Test
    public void test() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        List<Transmission> transmissions = session.createQuery("from Transmission ").list();
        transmissions.forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void name() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        List<Car> cars = session.createQuery("from Car ").list();
        cars.forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
    }
    public void setTransmission() {
        Transmission transmission = new Transmission();
        Transmission transmission2 = new Transmission();
        Transmission transmission3 = new Transmission();
        transmission.setType("FF");
        transmission2.setType("FR");
        transmission3.setType("4x4");
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(transmission);
        session.save(transmission2);
        session.save(transmission3);
        session.getTransaction().commit();
        session.close();
    }
    public void setEngine() {
        Engine gaz = new Engine();
        gaz.setVolume(1800);
        gaz.setFuel("gaz");
        Engine disel = new Engine();
        disel.setFuel("disel");
        disel.setVolume(1400);
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(gaz);
        session.save(disel);
        session.getTransaction().commit();
        session.close();
    }
}