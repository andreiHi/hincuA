package ru.job4j.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.model.Item;
import ru.job4j.service.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 11.03.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class AddItem extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(AddItem.class);
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void destroy() {
        super.destroy();
        sessionFactory.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("item");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Item item = new Item(description, new Timestamp(System.currentTimeMillis()), false);
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }
}
